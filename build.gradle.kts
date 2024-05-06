import java.net.URL

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

tasks {
    register("scrapeResources") {
        group = "update"

        val relicDiscoveryUrl = URL("https://www.mobilemeta.gg/honkai-starrail/database/relic")
        val characterDiscoveryUrl = URL("https://www.mobilemeta.gg/honkai-starrail/character")
        val cavernOfCorrosionUrl = URL("https://honkai-star-rail.fandom.com/wiki/Cavern_of_Corrosion")
        val simulatedUniverseUrl = URL("https://honkai-star-rail.fandom.com/wiki/Simulated_Universe/Worlds")
        val relicBaseUrl = "https://images.mobilemeta.gg/starrail/static/icon/relic"
        val characterBaseUrl = "https://images.mobilemeta.gg/starrail/images/characters"

        val relicData = String(relicDiscoveryUrl.readBytes())
        val corrosionData = String(cavernOfCorrosionUrl.readBytes())
        val simulatedWorldData = String(simulatedUniverseUrl.readBytes())
        val characterData = String(characterDiscoveryUrl.readBytes())

        @Suppress("SpellCheckingInspection")
        val labelRegex = Regex("<div class=\"font-starrailweb text-yellow-400\">([^<]+)</div>")
        val labels = labelRegex.findAll(relicData).map { match -> match.groupValues[1] }.toList()

        val imgRegex = Regex("/static/icon/relic/([13]\\d{2}).png")
        val imageCodes = imgRegex.findAll(relicData).map { match -> match.groupValues[1] }.toList()

        val cavernOfCorrosionRegex = Regex("title=\"Cavern of Corrosion: ([^\"]+)\"")
        val corrosionNames = cavernOfCorrosionRegex.findAll(corrosionData).map { match -> match.groupValues[1] }.toList()

        val relicCorrosionRegex = Regex("<span class=\"card-caption\" style=\"width:80px\">\\s*<a href=\"/wiki/[^\"]+\" title=\"([^\"]+)\">\\s*([^<]+)\\s*</a>\\s*</span>")
        val corrosionRelics = relicCorrosionRegex.findAll(corrosionData).map { match -> match.groupValues[1] }.toList()

        val simulatedUniverseRegex = Regex("<div class=\"card-container\"><span class=\"card-wrapper\"><span class=\"card-body card-2?345\"><span class=\"card-image\"><span><a href=\"/wiki/[^\"]+\" title=\"([^\"]+)\">")
        val worldOrnaments = simulatedUniverseRegex.findAll(simulatedWorldData).map { match -> match.groupValues[1] }.toList()

        val characterImageRegex = Regex("<img src=\"https://images.mobilemeta.gg/starrail/images/characters/([^/]+)/[^.]+.png\" alt=\"\" style=\"width:80px;height:82px\"/?>")
        val characterImages = characterImageRegex.findAll(characterData).map { match -> match.groupValues[1] }.toList()

        val characterNameRegex = Regex("<div class=\"w-20 overflow-hidden text-ellipsis\">([^<]+)</div>")
        val characterNames = characterNameRegex.findAll(characterData).map { match -> match.groupValues[1] }.toList()

        val suitableRelicRegex = Regex("<div class=\"h-20 w-20 rounded-full ring-2 ring-stone-900 hover:ring-yellow-500\"><a href=\"/honkai-starrail/character/([^\"]+)\">")
        val characterDatabase = characterImages.mapIndexed { i, code ->
            return@mapIndexed code to (characterNames[i] to mutableListOf<String>())
        }.toMap()

        assert(labels.size == imageCodes.size)
        assert(characterImages.size == characterNames.size)

        val generatedCodeFile = File("./app/src/main/java/edu/shch/hsr/relicanalyzer/hsr/HSR.kt")
        val generatedRelicEnum = StringBuilder()
        val generatedOrnamentEnum = StringBuilder()
        val labelFile = File("./app/src/main/res/values/dynamic-hsr.xml")

        //#region Resource Generation
        val labelResource = StringBuilder().appendLine("<resources>")
        for ((imageCode, label) in imageCodes.zip(labels)) {
            if (imageCode.startsWith("1")) {
                labelResource.appendLine("\t<string name=\"relic.${imageCode}\">${label}</string>")
                generatedRelicEnum.append("\t${label.toEnumField()}(R.string.relic_$imageCode, ")
                    .append("R.drawable.relic_${imageCode}_0, ")
                    .append("R.drawable.relic_${imageCode}_1, ")
                    .append("R.drawable.relic_${imageCode}_2, ")
                    .appendLine("R.drawable.relic_${imageCode}_3),")
                for (part in 0..3) {
                    val imageFile = File("./app/src/main/res/drawable/relic_${imageCode}_${part}.png")
                    if (!imageFile.exists()) {
                        imageFile.writeBytes(URL("$relicBaseUrl/${imageCode}_${part}.png").readBytes())
                    }
                }
            } else if (imageCode.startsWith("3")) {
                labelResource.appendLine("\t<string name=\"ornament.${imageCode}\">${label}</string>")
                val enumLabel = label.replace(Regex("[:,]"), "")
                    .uppercase().replace(Regex("[ -]"), "_")
                generatedOrnamentEnum.append("\t$enumLabel(R.string.ornament_$imageCode, ")
                    .append("R.drawable.ornament_${imageCode}_0, ")
                    .appendLine("R.drawable.ornament_${imageCode}_1),")
                for (part in 0..1) {
                    val imageFile = File("./app/src/main/res/drawable/ornament_${imageCode}_${part}.png")
                    if (!imageFile.exists()) {
                        imageFile.writeBytes(URL("$relicBaseUrl/${imageCode}_${part}.png").readBytes())
                    }
                }
            } else {
                throw IllegalStateException("The 'API' appears to have changed, please check!")
            }

            // Suitable Characters
            val relicUrl = URL("https://www.mobilemeta.gg/honkai-starrail/database/relic/$imageCode")
            val relicDBData = String(relicUrl.readBytes())
            val suitableCharacters = suitableRelicRegex.findAll(relicDBData).map { match -> match.groupValues[1] }.toList()
            // TODO: Fix issue with 'https://www.mobilemeta.gg/honkai-starrail/database/relic/118'
            // There, the Character URLs don't point to the characters, but to the generic character page!
            for (character in suitableCharacters) {
                val relicType = if (imageCode.startsWith("1")) "Relic" else "Ornament"
                characterDatabase[character]!!.second.add("${relicType}.${label.toEnumField()}")
            }
        }
        for ((name, img) in characterNames.zip(characterImages)) {
            val lowerEnum = name.toEnumField().lowercase()
            labelResource.appendLine("\t<string name=\"character.${lowerEnum}\">${name}</string>")
            val imageFile = File("./app/src/main/res/drawable/character_${lowerEnum}.png")
            if (!imageFile.exists()) {
                imageFile.writeBytes(URL("$characterBaseUrl/${img}/${img}.png").readBytes())
            }
        }
        labelResource.appendLine("</resources>")
        labelFile.writeText(labelResource.toString())
        //#endregion

        //#region Code Generation
        generatedCodeFile.writeText(buildString {
            appendLine("package edu.shch.hsr.relicanalyzer.hsr").appendLine()
            appendLine("import androidx.annotation.DrawableRes")
            appendLine("import androidx.annotation.StringRes")
            appendLine("import edu.shch.hsr.relicanalyzer.R").appendLine()
            appendLine("@Suppress(\"SpellCheckingInspection\")")
            appendLine("enum class Relic (")
            appendLine("\t@StringRes val text: Int,")
            appendLine("\t@DrawableRes val head: Int,")
            appendLine("\t@DrawableRes val hands: Int,")
            appendLine("\t@DrawableRes val body: Int,")
            appendLine("\t@DrawableRes val feet: Int")
            appendLine(") : GenericRelic {")
            append(generatedRelicEnum)
            appendLine("}").appendLine()
            appendLine("@Suppress(\"SpellCheckingInspection\")")
            appendLine("enum class Ornament (")
            appendLine("\t@StringRes val text: Int,")
            appendLine("\t@DrawableRes val planarSphere: Int,")
            appendLine("\t@DrawableRes val linkRope: Int")
            appendLine(") : GenericRelic {")
            append(generatedOrnamentEnum)
            appendLine("}").appendLine()
            appendLine("@Suppress(\"SpellCheckingInspection\")")
            appendLine("enum class CavernOfCorrosion(vararg val sets: Relic) {")
            var cavernOfCorrosionCounter = 0
            while (cavernOfCorrosionCounter < corrosionNames.size) {
                val name = corrosionNames[cavernOfCorrosionCounter].toEnumField()
                append("\t$name(")
                val relicIndex = cavernOfCorrosionCounter * 2
                @Suppress("SpellCheckingInspection")
                if (relicIndex < corrosionRelics.size) {
                    val firstSet = corrosionRelics[relicIndex].toEnumField()
                    val secondSet = corrosionRelics[relicIndex + 1].toEnumField()
                    append("Relic.${firstSet}, Relic.$secondSet")
                } else if(name == "PATH_OF_DREAMDIVE") { // FIXME: HOTFIX WHILE THE 'API' IS BEING DUMB
                    append("Relic.PIONEER_DIVER_OF_DEAD_WATERS, Relic.WATCHMAKER_MASTER_OF_DREAM_MACHINATIONS")
                }
                appendLine("),")
                cavernOfCorrosionCounter++
            }
            appendLine("}").appendLine()
            appendLine("enum class SimulatedUniverse(vararg val sets: Ornament) {")
            for (world in 0 until worldOrnaments.size / 2) {
                val firstSet = worldOrnaments[2*world].toEnumField()
                val secondSet = worldOrnaments[2*world+1].toEnumField()
                appendLine("\tWORLD_${world}(Ornament.${firstSet}, Ornament.${secondSet}),")
            }
            appendLine("}").appendLine()
            appendLine("@Suppress(\"SpellCheckingInspection\")")
            appendLine("enum class Character(")
            appendLine("\t@StringRes val characterName: Int,")
            appendLine("\t@DrawableRes val img: Int,")
            appendLine("\tvararg val preferredRelics: GenericRelic")
            appendLine(") {")
            for ((name, relics) in characterDatabase.values) {
                val enum = name.toEnumField()
                append("\t${enum}(R.string.character_${enum.lowercase()}, R.drawable.character_${enum.lowercase()}, ")
                for (relic in relics) {
                    append("$relic, ")
                }
                appendLine("),")
            }
            appendLine("}").appendLine()
        })
        //#endregion
    }
}

fun String.toEnumField() =
    this.replace(Regex("[:,]"), "")
        .replace(Regex("[()]"), " ")
        .replace(Regex(" +"), " ").trim()
        .uppercase().replace(Regex("[ -.]"), "_")