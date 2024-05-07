import java.net.URL

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

// Constants
val suppressSpellCheck = "@Suppress(\"SpellCheckingInspection\")"

val importWriteAndDraw = """
    import androidx.annotation.StringRes
    import androidx.annotation.DrawableRes
    import edu.shch.hsr.relicanalyzer.R
""".trimIndent()

val codePackage = "package edu.shch.hsr.relicanalyzer.hsr.dynamic"
val codeBasePath = "./app/src/main/java/edu/shch/hsr/relicanalyzer/hsr/dynamic"
val drawableBasePath = "./app/src/main/res/drawable"
val dataBasePath = "./app/src/main/res/values"

fun String.asField() =
    this.replace(Regex("[:,]"), "")
        .replace(Regex("[()]"), " ")
        .replace(Regex(" +"), " ").trim()
        .uppercase().replace(Regex("[ -.]"), "_")

class ScrapeTask(
    private val url: URL,
    private val regexes: List<Regex>,
    private val writers: List<(List<List<String>>) -> Unit>,
    private val actions: List<(MatchResult) -> String> = mutableListOf<(MatchResult) -> String>().also {
        for (i in regexes.indices) {
            it.add { match -> match.groupValues[1] }
        }
    }
) {
    fun scrape() {
        val data = String(url.readBytes())
        val results = regexes.zip(actions).map { (regex, action) ->
            regex.findAll(data).map(action).toList()
        }
        writers.forEach { it(results) }
    }
}

tasks {
    val scrapeCharacters = register("scrapeCharacters") {
        group = "scraping"
        ScrapeTask(
            URL("https://www.mobilemeta.gg/honkai-starrail/character"),
            listOf(
                Regex("<img src=\"https://images.mobilemeta.gg/starrail/images/characters/([^/]+)/[^.]+.png\" alt=\"\" style=\"width:80px;height:82px\"/?>"),
                Regex("<div class=\"w-20 overflow-hidden text-ellipsis\">([^<]+)</div>")
            ),
            listOf {
                val imageCodes = it[0]
                val names = it[1]
                assert(imageCodes.size == names.size)
                val characterBaseUrl = "https://images.mobilemeta.gg/starrail/images/characters"
                val charactersFile = File("${dataBasePath}/dynamic-characters.xml")
                val characterContent = StringBuilder()
                for ((code, name) in imageCodes.zip(names)) {
                    val enumName = name.asField().lowercase()
                    characterContent.appendLine("<string name=\"character.${enumName}\">${name}</string>")
                    val image = File("${drawableBasePath}/character_${enumName}.png")
                    if (!image.exists()) {
                        image.writeBytes(URL("$characterBaseUrl/${code}/${code}.png").readBytes())
                    }
                }
                charactersFile.writeText(
                    """
                <resources>
                    $characterContent
                </resources>
            """.trimIndent()
                )
            }
        ).scrape()
    }

    val scrapeRelics = register("scrapeRelics") {
        group = "scraping"
        ScrapeTask(
            URL("https://www.mobilemeta.gg/honkai-starrail/database/relic"),
            listOf(
                Regex("<div class=\"font-starrailweb text-yellow-400\">([^<]+)</div>"),
                Regex("/static/icon/relic/([13]\\d{2}).png")
            ),
            listOf {
                val labels = it[0]
                val imageCodes = it[1]
                assert(labels.size == imageCodes.size)
                val relicsBaseUrl = "https://images.mobilemeta.gg/starrail/static/icon/relic"

                val relicsCodeFile = File("${codeBasePath}/Relics.kt")
                val relicsCodeContent = mutableListOf<String>()

                val ornamentsCodeFile = File("${codeBasePath}/Ornaments.kt")
                val ornamentsCodeContent = mutableListOf<String>()

                val relicsLangFile = File("${dataBasePath}/dynamic-relics.xml")
                val relicsLangContent = mutableListOf<String>()

                for ((code, label) in imageCodes.zip(labels)) {
                    val isRelic = code.startsWith("1")
                    val isOrnament = code.startsWith("3")
                    if (!isRelic && !isOrnament) throw IllegalStateException("The 'API' appears to have changed, please check!")
                    val relicType = if (isRelic) "relic" else "ornament"
                    val enumName = label.asField()
                    relicsLangContent.add("<string name=\"${relicType}.${code}\">${label}</string>")

                    val listRef = if (isRelic) relicsCodeContent else ornamentsCodeContent

                    var relicEnumValue = "${enumName}(R.string.${relicType}_${code}, "
                    val iterations = if(isRelic) 4 else 2
                    for (i in 0 until iterations) {
                        relicEnumValue += "R.drawable.${relicType}_${code}_${i}, "
                        val imageFile = File("${drawableBasePath}/${relicType}_${code}_${i}.png")
                        if (!imageFile.exists()) {
                            imageFile.writeBytes(URL("$relicsBaseUrl/${code}_${i}.png").readBytes())
                        }
                    }
                    listRef.add("${relicEnumValue})")
                }
                relicsLangFile.writeText("<resources>\n\t${relicsLangContent.joinToString("\n\t")}\n</resources>")
                relicsCodeFile.writeText(
                    "${codePackage}\n\n${importWriteAndDraw}\n" + """
                    import edu.shch.hsr.relicanalyzer.hsr.GenericRelic
                    
                    $suppressSpellCheck
                    enum class Relic (
                        @StringRes val text: Int,
                        @DrawableRes val head: Int,
                        @DrawableRes val hands: Int,
                        @DrawableRes val body: Int,
                        @DrawableRes val feet: Int
                    ) : GenericRelic {
                """.trimIndent() + "\n\t${relicsCodeContent.joinToString(",\n\t")}\n}")

                ornamentsCodeFile.writeText(
                    "${codePackage}\n\n${importWriteAndDraw}\n" + """
                    import edu.shch.hsr.relicanalyzer.hsr.GenericRelic

                    $suppressSpellCheck
                    enum class Ornament (
                        @StringRes val text: Int,
                        @DrawableRes val planarSphere: Int,
                        @DrawableRes val linkRope: Int
                    ): GenericRelic {
                """.trimIndent() + "\n\t${ornamentsCodeContent.joinToString(",\n\t")}\n}")
            }
        ).scrape()
    }

    val scrapeCaverns = register("scrapeCaverns") {
        group = "scraping"
        ScrapeTask(
            URL("https://honkai-star-rail.fandom.com/wiki/Cavern_of_Corrosion"),
            listOf(
                Regex("title=\"Cavern of Corrosion: ([^\"]+)\""),
                Regex("<span class=\"card-caption\" style=\"width:80px\">\\s*<a href=\"/wiki/[^\"]+\" title=\"([^\"]+)\">\\s*([^<]+)\\s*</a>\\s*</span>")
            ),
            listOf {
                val names = it[0]
                val pairings = it[1]
                // TODO: Add [names.size == pairings.size / 2]-assertion
                val relicsFile = File("${codeBasePath}/CavernOfCorrosion.kt")
                val relicsContent = buildString {
                    appendLine(codePackage).appendLine()
                    appendLine("@Suppress(\"SpellCheckingInspection\")")
                    appendLine("enum class CavernOfCorrosion(vararg val sets: Relic) {")
                    for ((i, name) in names.withIndex()) {
                        val cavernName = name.asField()
                        append("\t$cavernName(")
                        val relicIndex = i * 2
                        @Suppress("SpellCheckingInspection")
                        if (relicIndex < pairings.size) {
                            val firstSet = pairings[relicIndex].asField()
                            val secondSet = pairings[relicIndex + 1].asField()
                            append("Relic.${firstSet}, Relic.$secondSet")
                        } else if(cavernName == "PATH_OF_DREAMDIVE") {
                            // FIXME: HOTFIX WHILE THE 'API' IS BEING DUMB
                            append("Relic.PIONEER_DIVER_OF_DEAD_WATERS, ")
                            append("Relic.WATCHMAKER_MASTER_OF_DREAM_MACHINATIONS")
                        }
                        appendLine("),")
                    }
                    appendLine("}")
                }
                relicsFile.writeText(relicsContent)
            }
        ).scrape()
    }

    val scrapeWorlds = register("scrapeWorlds") {
        group = "scraping"
        ScrapeTask(
            URL("https://honkai-star-rail.fandom.com/wiki/Simulated_Universe/Worlds"),
            listOf(Regex("<div class=\"card-container\"><span class=\"card-wrapper\"><span class=\"card-body card-2?345\"><span class=\"card-image\"><span><a href=\"/wiki/[^\"]+\" title=\"([^\"]+)\">")),
            listOf {
                val ornaments = it[0]
                val worldsFile = File("${codeBasePath}/Worlds.kt")
                val worldsContent = mutableListOf<String>()
                for (world in 0 until ornaments.size / 2) {
                    val firstSet = ornaments[2*world].asField()
                    val secondSet = ornaments[2*world+1].asField()
                    worldsContent.add("WORLD_${world + 3}(Ornament.${firstSet}, Ornament.${secondSet})")
                }
                worldsFile.writeText(
                    "$codePackage\n\n" +
                    "enum class SimulatedUniverse(vararg val sets: Ornament) {\n\t" +
                            worldsContent.joinToString(",\n\t") + "\n}"
                )
            }
        ).scrape()
    }

    register("scrapeResources") {
        group = "scraping"
        dependsOn(
            scrapeCharacters, scrapeRelics,
            scrapeCaverns, scrapeWorlds
        )
    }
}