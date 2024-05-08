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
                val charactersCodeFile = File("${codeBasePath}/Characters.kt")
                val characterCodeContent = mutableListOf<String>()
                val charactersLangFile = File("${dataBasePath}/dynamic-characters.xml")
                val characterLangContent = mutableListOf<String>()
                for ((code, name) in imageCodes.zip(names)) {
                    val enumName = name.asField()
                    characterCodeContent.add(enumName)
                    val idName = enumName.lowercase()
                    characterLangContent.add("<string name=\"character.${idName}\">${name}</string>")
                    val image = File("${drawableBasePath}/character_${idName}.png")
                    if (!image.exists()) {
                        image.writeBytes(URL("$characterBaseUrl/${code}/${code}.png").readBytes())
                    }
                }
                charactersLangFile.writeText("<resources>\n\t${characterLangContent.joinToString("\n\t")}\n</resources>")
                charactersCodeFile.writeText(buildString {
                    appendLine(codePackage).appendLine()
                    appendLine("enum class Character {")
                    for (characterName in characterCodeContent) {
                        appendLine("\t$characterName,")
                    }
                    appendLine("}")
                })
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

    val scrapeRelicPreferences = register("scrapeRelicPreferences") {
        group = "scraping"

        val characterDatabase = mutableMapOf<Int, String>()
        val relicDatabase = mutableMapOf<String, List<String>>()

        val characterIdRegex = Regex("\\\"characterId\\\":\\\"(\\d+)\\\"")
        val characterImgRegex = Regex("src=\"https://images.mobilemeta.gg/starrail/static/icon/character/(\\d+).png\"")
        val characterNameRegex = Regex("<div class=\"font-starrailweb text-shadow-emerald-500 text-3xl text-white\" style=\"text-shadow: rgb(255, 255, 255) 2px 2px;\">([^<]+)</div>")

        val relicNameRegex = Regex("<div class=\"font-starrailweb my-2 text-2xl font-bold text-stone-200\">([^<]+)</div>")

        ScrapeTask(
            URL("https://www.mobilemeta.gg/honkai-starrail/character"),
            listOf(Regex("href=\"/honkai-starrail/character/([^\"]+)\"")),
            listOf {
                val characters = it[0]
                for (character in characters) {
                    val characterUrl = URL("https://www.mobilemeta.gg/honkai-starrail/character/$character")
                    val characterData = String(characterUrl.readBytes())
                    val characterId = characterIdRegex.find(characterData)
                        ?: throw IllegalStateException("Could not find Character ID for $character!")
                    val characterName = characterNameRegex.find(characterData)
                        ?: throw IllegalStateException("Could not find Character Name for $character!")
                    characterDatabase[characterId.groupValues[1].toInt()] = characterName.groupValues[1].asField()
                }
            }
        ).scrape()

        ScrapeTask(
            URL("https://www.mobilemeta.gg/honkai-starrail/database/relic"),
            listOf(
                Regex("href=\"/honkai-starrail/database/relic/(\\d+)\"")
            ),
            listOf {
                val relicIds = it[0]
                for (relicId in relicIds) {
                    val relicUrl = URL("https://www.mobilemeta.gg/honkai-starrail/database/relic/$relicId")
                    val relicData = String(relicUrl.readBytes())
                    val relicName = relicNameRegex.find(relicData)
                        ?: throw IllegalStateException("Could not find the Relic Name for Relic: $relicId")
                    val characterIds = characterImgRegex.findAll(relicData)
                    val relicType = if (relicId.startsWith("1")) "Relic" else "Ornament"
                    relicDatabase["${relicType}.${relicName.groupValues[1].asField()}"] = characterIds.map { match ->
                        characterDatabase[match.groupValues[1].toInt()]
                            ?: throw IllegalStateException("There is some fuckin' error... Good Luck...")
                    }.toList()
                }
            }
        ).scrape()

        val relicPreferencesFile = File("${codeBasePath}/RelicPreferences.kt")
        relicPreferencesFile.writeText(buildString {
            appendLine(codePackage).appendLine()
            appendLine("enum RelicPreference(val relic: GenericRelic, vararg val characters: Character) {")
            for ((relic, characters) in relicDatabase) {
                append("\t${relic.split('.')[1]}(${relic}, ")
                for (character in characters) {
                    append("Character.${character}, ")
                }
                appendLine("),")
            }
            appendLine("}")
        })    }

    register("devScraper") {
        group = "scraping"
        val url = URL("https://www.mobilemeta.gg/honkai-starrail/character/aventurine")
        println(String(url.readBytes()))
    }

    register("scrapeResources") {
        group = "scraping"
        dependsOn(
            scrapeCharacters, scrapeRelics,
            scrapeCaverns, scrapeWorlds,
            scrapeRelicPreferences
        )
    }
}