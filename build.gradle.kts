import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.beust.klaxon.PathMatcher
import org.jetbrains.kotlin.backend.common.push
import java.io.StringReader
import java.net.URL

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

buildscript {
    dependencies {
        classpath(libs.klaxon)
    }
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
    this.replace(Regex("[:,?]"), "")
        .replace(Regex("[()•]"), " ")
        .replace(Regex(" +"), " ").trim()
        .uppercase()
        .replace(Regex("[ -.]"), "_")
        .replace(Regex("_+"), "_")

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
    val scrapeRelics = register("scrapeRelics") {
        group = "scraping"
        ScrapeTask(
            URL("https://www.mobilemeta.gg/honkai-starrail/database/relic"),
            listOf(
                Regex("<div class=\"font-starrailweb text-yellow-400\">([^<]+)</div>"),
                Regex("/static/icon/relic/([13]\\d{2}).png"),
                Regex("<div class=\"font-starrailweb text-sm\\s+text-stone-300\">\\s*<span class=\"mx-2 text-yellow-500\">Set\\D*\\d<\\/span>([^<]+)")
            ),
            listOf {
                val labels = it[0]
                val imageCodes = it[1]
                val setEffects = it[2]

                assert(labels.size == imageCodes.size)

                val relicsBaseUrl = "https://images.mobilemeta.gg/starrail/static/icon/relic"

                val relicsCodeFile = File("${codeBasePath}/Relics.kt")
                val relicsCodeContent = mutableListOf<String>()

                val ornamentsCodeFile = File("${codeBasePath}/Ornaments.kt")
                val ornamentsCodeContent = mutableListOf<String>()

                val relicsLangFile = File("${dataBasePath}/dynamic-relics.xml")
                val relicsLangContent = mutableListOf<String>()

                var setEffectIndex = 0
                for ((index, code) in imageCodes.withIndex()) {
                    val label = labels[index]

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

                    if (isRelic) {
                        val halfSet = setEffects[setEffectIndex].replace("&#x27;", "\\'")
                        val fullSet = setEffects[setEffectIndex + 1].replace("&#x27;", "\\'")
                        relicsLangContent.add("<string name=\"${relicType}.${code}.half\">${halfSet}</string>")
                        relicsLangContent.add("<string name=\"${relicType}.${code}.full\">${fullSet}</string>")
                        relicEnumValue += "R.string.${relicType}_${code}_half, R.string.${relicType}_${code}_full"
                        setEffectIndex += 2
                    } else {
                        val set = setEffects[setEffectIndex].replace("&#x27;", "\\'")
                        relicsLangContent.add("<string name=\"${relicType}.${code}.set\">${set}</string>")
                        relicEnumValue += "R.string.${relicType}_${code}_set"
                        setEffectIndex += 1
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
                        @DrawableRes val feet: Int,
                        @StringRes val halfSet: Int,
                        @StringRes val fullSet: Int,
                    ) : GenericRelic {
                """.trimIndent() + "\n\t${relicsCodeContent.joinToString(",\n\t")}\n}")

                ornamentsCodeFile.writeText(
                    "${codePackage}\n\n${importWriteAndDraw}\n" + """
                    import edu.shch.hsr.relicanalyzer.hsr.GenericRelic

                    $suppressSpellCheck
                    enum class Ornament (
                        @StringRes val text: Int,
                        @DrawableRes val planarSphere: Int,
                        @DrawableRes val linkRope: Int,
                        @StringRes val set: Int,
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

    val scrapeCharacters = register("scrapeCharacters") {
        group = "scraping"
        val charactersUrl = URL("https://www.prydwen.gg/page-data/star-rail/characters/page-data.json")
        val rawCharacters = String(charactersUrl.readBytes())
        val characterRegex = Regex(
            listOf(
                ".*element$",
                ".*path$",
                ".*rarity$",
                ".*cardImage\\..*\\.images\\.fallback\\.src",
                ".*fullImage\\..*\\.images\\.fallback\\.src",
                ".*buildData\\[\\d+\\]\\.relics\\[\\d+\\].relic(_2)?",
                ".*buildData\\[\\d+\\]\\.planars\\[\\d+\\].planar",
                ".*buildData\\[\\d+\\]\\.body\\[\\d+\\].stat",
                ".*buildData\\[\\d+\\]\\.feet\\[\\d+\\].stat",
                ".*buildData\\[\\d+\\]\\.rope\\[\\d+\\].stat",
                ".*buildData\\[\\d+\\]\\.sphere\\[\\d+\\].stat",
                // TODO: Potentially parse sub-stat-priority
            ).joinToString("|") { "($it)" })
        val charactersRegex = Regex(".*(slug|name)$")

        val characterDatabase = mutableMapOf<String, String>()
        val characterCodeFile = File("${codeBasePath}/Characters.kt")
        val characterCodeContent = StringBuilder()
        val characterLangFile = File("${dataBasePath}/dynamic-characters.xml")
        val characterLangContent = StringBuilder()

        Klaxon().pathMatcher(object: PathMatcher {
            var slug: String? = null
            override fun onMatch(path: String, value: Any) {
                if (path.endsWith("slug")) {
                    slug = value.toString()
                } else if (path.endsWith("name")) {
                    characterDatabase[this.slug!!] = value.toString()
                }
            }
            override fun pathMatches(path: String) = charactersRegex.matches(path)
        }).parseJsonObject(StringReader(rawCharacters))
        for ((slug, character) in characterDatabase) {
            val charEnumName = character.asField()
            val charVarName = charEnumName.lowercase()

            val characterUrl = URL("https://www.prydwen.gg/page-data/star-rail/characters/$slug/page-data.json")
            val rawCharacter = String(characterUrl.readBytes())

            var element: String? = null
            var charPath: String? = null
            var rarity = 0
            var hasSplash = false
            val preferredRelics = mutableListOf<Pair<String, String>>()
            val preferredOrnaments = mutableListOf<String>()
            val bodyStats = mutableListOf<String>()
            val feetStats = mutableListOf<String>()
            val ropeStats = mutableListOf<String>()
            val sphereStats = mutableListOf<String>()

            Klaxon().pathMatcher(object: PathMatcher {
                override fun onMatch(path: String, value: Any) {
                    val stringVal = value.toString()
                    if (path.contains("cardImage")) {
                        val ext = stringVal.split(".").last()
                        val filePath = "${drawableBasePath}/character_${charVarName}_icon.${ext}"
                        val file = File(filePath)
                        if (file.exists()) return
                        file.writeBytes(URL("https://www.prydwen.gg$value").readBytes())
                    } else if (path.contains("fullImage")) {
                        hasSplash = true
                        val ext = stringVal.split(".").last()
                        val filePath = "${drawableBasePath}/character_${charVarName}_splash.${ext}"
                        val file = File(filePath)
                        if (file.exists()) return
                        file.writeBytes(URL("https://www.prydwen.gg$value").readBytes())
                    } else if (path.endsWith("element")) {
                        element = stringVal.uppercase()
                    } else if (path.endsWith("path")) {
                        charPath = stringVal.uppercase()
                    } else if (path.endsWith("rarity")) {
                        rarity = stringVal.toInt()
                    } else if (path.endsWith("relic")) {
                        preferredRelics.add(stringVal.asField() to "")
                    } else if (path.endsWith("relic_2")) {
                        if (stringVal.isNotBlank()) {
                            preferredRelics[preferredRelics.lastIndex] =
                                preferredRelics[preferredRelics.lastIndex].copy(
                                    second = stringVal.asField())
                        }
                    } else if (path.endsWith("planar")) {
                        preferredOrnaments.push(stringVal.asField())
                    } else if (path.contains("body")) {
                        bodyStats.add(stringVal)
                    } else if (path.contains("feet")) {
                        feetStats.add(stringVal)
                    } else if (path.contains("rope")) {
                        ropeStats.add(stringVal)
                    } else if (path.contains("sphere")) {
                        sphereStats.add(stringVal)
                    } else {
                        throw IllegalStateException("Weird Match: ($path, $value) for $character")
                    }
                }
                override fun pathMatches(path: String) = characterRegex.matches(path)
            }).parseJsonObject(StringReader(rawCharacter))

            characterLangContent.appendLine("\t<string name=\"character.${charVarName}\">" +
                    "${character.replace("&", "&amp;")}</string>")

            characterCodeContent
                .append("\t${charEnumName}(")
                .append("R.string.character_${charVarName}, ")
                .append("R.drawable.character_${charVarName}_icon, ")
                .append(if (hasSplash) "R.drawable.character_${charVarName}_splash, " else "null, ")
                .append("Rarity.fromId(${rarity}), ")
                .append("Element.${element}, ")
                .append("Path.${charPath}, ")
                .append("listOf(")
            // Add Preferred Relic Sets
            for ((first, second) in preferredRelics) {
                characterCodeContent.append("(Relic.$first ")
                if (second.isBlank()) {
                    characterCodeContent.append("to null), ")
                } else {
                    characterCodeContent.append("to Relic.$second), ")
                }
            }
            characterCodeContent.append("), listOf(")
            // Add Preferred Planar Sets
            for (ornament in preferredOrnaments) {
                characterCodeContent.append("Ornament.$ornament, ")
            }
            characterCodeContent.append("), ")
            // Add Body Stats
            val stats = listOf(bodyStats, feetStats, ropeStats, sphereStats)
            for (partStats in stats) {
                characterCodeContent.append("listOf(")
                for (stat in partStats) {
                    characterCodeContent.append("Statistic.fromString(\"${stat}\"), ")
                }
                characterCodeContent.append("), ")
            }
            characterCodeContent.appendLine("),")
        }
        characterCodeFile.writeText(buildString {
            appendLine(codePackage).appendLine()
            appendLine(importWriteAndDraw).appendLine()
            appendLine("import edu.shch.hsr.relicanalyzer.hsr.Element")
            appendLine("import edu.shch.hsr.relicanalyzer.hsr.Path")
            appendLine("import edu.shch.hsr.relicanalyzer.hsr.Rarity")
            appendLine("import edu.shch.hsr.relicanalyzer.hsr.Statistic")
            appendLine()
            appendLine(suppressSpellCheck)
            appendLine("enum class Character (")
            appendLine("\t@StringRes val charName: Int,")
            appendLine("\t@DrawableRes val icon: Int,")
            appendLine("\t@DrawableRes val splash: Int?,")
            appendLine("\tval rarity: Rarity,")
            appendLine("\tval element: Element,")
            appendLine("\tval path: Path,")
            appendLine("\tval relics: List<Pair<Relic, Relic?>>,")
            appendLine("\tval ornaments: List<Ornament>,")
            appendLine("\tval bodyStats: List<Statistic>,")
            appendLine("\tval feetStats: List<Statistic>,")
            appendLine("\tval ropeStats: List<Statistic>,")
            appendLine("\tval sphereStats: List<Statistic>,")
            appendLine(") {")
            append(characterCodeContent.toString())
            appendLine("}")
        })
        characterLangFile.writeText(buildString {
            appendLine("<resources>")
            append(characterLangContent)
            appendLine("</resources>")
        })
    }

    val scrapeLightCones = register("scrapeLightCones") {
        group = "scraping"
        val lightConesUrl = URL("https://www.prydwen.gg/page-data/star-rail/light-cones/page-data.json")
        val rawLightCones = String(lightConesUrl.readBytes())
        val lightConeRegex = Regex(listOf(
            ".*name$",
            ".*path$",
            ".*rarity$",
            ".*stats\\.(hp|atk|def)\\.value_level_max$",
            ".*skillName$",
            ".*skillDescription\\.raw$"
            // TODO: Potentially parse sub-stat-priority
        ).joinToString("|") { "($it)" })

        val lightConeImgRegex = Regex("<figure class=\"pi-item pi-image\">\\s*<a href=\"(https://static.wikia.nocookie.net/houkai-star-rail/images/[^\"]+)\" class=\"image image-thumbnail\"")
        var lightConeName: String? = null
        var pathName: String? = null
        var rarity = 0
        var stats: Triple<Int, Int, Int> = Triple(-1, -1, -1)

        val codeContent = StringBuilder()
        fun dumpLightCone() {
            val enumName = lightConeName!!.asField()
            val varName = enumName.lowercase()
            with(codeContent) {
                append("\t${enumName}(R.string.light_cone_${varName}, ")
                append("R.drawable.light_cone_${varName}_lc, ")
                append("R.drawable.light_cone_${varName}_art, ")
                append("R.drawable.light_cone_${varName}_icon, ")
                append("Path.${pathName!!.uppercase()}, ")
                append("Rarity.fromId(${rarity}), ")
                append("${stats.first}, ${stats.second}, ${stats.third}, ")
                append("R.string.light_cone_skill_${varName}, ")
                appendLine("R.string.light_cone_description_${varName}),")
            }
        }

        val codeFile = File("${codeBasePath}/LightCones.kt")
        val langFile = File("${dataBasePath}/dynamic-light-cones.xml")
        val langContent = StringBuilder()

        Klaxon().pathMatcher(object: PathMatcher {
            override fun onMatch(path: String, value: Any) {
                if (path.endsWith("name")) {
                    if (lightConeName != null) dumpLightCone()

                    lightConeName = value.toString()
                    val varName = lightConeName!!.asField().lowercase()
                    langContent.appendLine("\t<string name=\"light_cone.${varName}\">${
                        lightConeName!!.replace("'", "\\'")
                    }</string>")
                    var slug = lightConeName!!
                        .replace(" ", "_")
                        .replace("?", "%3F")

                    // Exception Handling
                    if (slug == "Data_Bank") slug = "Data_Bank_(Light_Cone)"

                    val imageMine = URL("https://honkai-star-rail.fandom.com/wiki/$slug")
                    val rawWikiPage = String(imageMine.readBytes())
                    val matches = lightConeImgRegex.findAll(rawWikiPage).toList()
                    for ((index, match) in matches.withIndex()) {
                        val url = URL(match.groupValues[1])
                        val file = when(index) {
                            0 -> File("${drawableBasePath}/light_cone_${varName}_lc.png")
                            1 -> File("${drawableBasePath}/light_cone_${varName}_art.png")
                            2 -> File("${drawableBasePath}/light_cone_${varName}_icon.png")
                            else -> throw IllegalArgumentException("Too many images found for: $value")
                        }
                        if (!file.exists()) {
                            file.writeBytes(url.readBytes())
                        }
                    }
                    if (matches.size != 3) {
                        throw MissingResourceException("Could not find all resources for Light Cone: $value")
                    }
                } else if (path.endsWith("path")) {
                    pathName = value.toString().uppercase()
                } else if (path.endsWith("rarity")) {
                    rarity = value.toString().toInt()
                } else if (path.endsWith("skillName")) {
                    val varName = lightConeName!!.asField().lowercase()
                    langContent.appendLine("\t<string name=\"light_cone.skill.${varName}\">${
                        value.toString().replace("'", "\\'")
                    }</string>")
                } else if (path.endsWith("raw")) {
                    val varName = lightConeName!!.asField().lowercase()

                    val markup = Klaxon().parseJsonObject(StringReader(value.toString()))
                    val parsedText = markup.array<JsonObject>("content")!!.first()
                        .array<JsonObject>("content")!!.joinToString("") {
                            val cleanValue = it.string("value")!!
                                .replace("'", "\\'")

                            if (it.array<JsonObject>("marks")?.isNotEmpty() == false) {
                                cleanValue
                            } else {
                                // always assume bold
                                "**${cleanValue}**"
                            }
                        }.trim()

                    with(langContent) {
                        appendLine("\t<string name=\"light_cone.description.${varName}\">${parsedText}</string>")
                    }
                } else if (path.endsWith("value_level_max")) {
                    stats = if (path.contains("hp")) {
                        stats.copy(first = value.toString().toInt())
                    } else if (path.contains("atk")) {
                        stats.copy(second = value.toString().toInt())
                    } else {
                        stats.copy(third = value.toString().toInt())
                    }
                }
            }
            override fun pathMatches(path: String) = lightConeRegex.matches(path)
        }).parseJsonObject(StringReader(rawLightCones))
        dumpLightCone()

        codeFile.writeText(buildString {
            appendLine(codePackage).appendLine()
            appendLine(importWriteAndDraw).appendLine()
            appendLine("import edu.shch.hsr.relicanalyzer.hsr.Path")
            appendLine("import edu.shch.hsr.relicanalyzer.hsr.Rarity")
            appendLine()
            appendLine(suppressSpellCheck)
            appendLine("enum class LightCone (")
            appendLine("\t@StringRes val text: Int,")
            appendLine("\t@DrawableRes val lightCone: Int,")
            appendLine("\t@DrawableRes val art: Int?,")
            appendLine("\t@DrawableRes val icon: Int?,")
            appendLine("\tval path: Path,")
            appendLine("\tval rarity: Rarity,")
            appendLine("\tval hp: Int,")
            appendLine("\tval atk: Int,")
            appendLine("\tval def: Int,")
            appendLine("\t@StringRes val skill: Int,")
            appendLine("\t@StringRes val description: Int")
            appendLine(") {")
            append(codeContent.toString())
            appendLine("}")
        })

        langFile.writeText(buildString {
            appendLine("<resources>")
            append(langContent)
            appendLine("</resources>")
        })
    }

    register("scrapeResources") {
        group = "scraping"
        dependsOn(
            scrapeCharacters, scrapeRelics,
            scrapeCaverns, scrapeWorlds,
            scrapeLightCones
        )
    }
}