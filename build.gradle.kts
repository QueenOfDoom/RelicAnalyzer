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
        val cavernOfCorrosionUrl = URL("https://honkai-star-rail.fandom.com/wiki/Cavern_of_Corrosion")
        val simulatedUniverseUrl = URL("https://honkai-star-rail.fandom.com/wiki/Simulated_Universe/Worlds")
        val resourceBaseUrl = "https://images.mobilemeta.gg/starrail/static/icon/relic"

        val relicData = String(relicDiscoveryUrl.readBytes())
        val corrosionData = String(cavernOfCorrosionUrl.readBytes())
        val simulatedWorldData = String(simulatedUniverseUrl.readBytes())

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

        assert(labels.size == imageCodes.size)

        val generatedCodeFile = File("./app/src/main/java/edu/shch/hsr/relicanalyzer/hsr/HSR.kt")
        val generatedRelicEnum = StringBuilder()
        val generatedOrnamentEnum = StringBuilder()
        val labelFile = File("./app/src/main/res/values/dynamic-relics.xml")

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
                        imageFile.writeBytes(URL("$resourceBaseUrl/${imageCode}_${part}.png").readBytes())
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
                        imageFile.writeBytes(URL("$resourceBaseUrl/${imageCode}_${part}.png").readBytes())
                    }
                }
            } else {
                throw IllegalStateException("The 'API' appears to have changed, please check!")
            }
        }
        labelResource.appendLine("</resources>")
        labelFile.writeText(labelResource.toString())


        generatedCodeFile.writeText(buildString {
            appendLine("package edu.shch.hsr.relicanalyzer.hsr").appendLine()
            appendLine("import androidx.annotation.DrawableRes")
            appendLine("import androidx.annotation.StringRes")
            appendLine("import edu.shch.hsr.relicanalyzer.R").appendLine()
            appendLine("@Suppress(\"SpellCheckingInspection\")")
            appendLine("enum class Relic(")
            appendLine("\t@StringRes val text: Int,")
            appendLine("\t@DrawableRes val head: Int,")
            appendLine("\t@DrawableRes val hands: Int,")
            appendLine("\t@DrawableRes val body: Int,")
            appendLine("\t@DrawableRes val feet: Int")
            appendLine(") {")
            append(generatedRelicEnum)
            appendLine("}").appendLine()
            appendLine("@Suppress(\"SpellCheckingInspection\")")
            appendLine("enum class Ornament(")
            appendLine("\t@StringRes val text: Int,")
            appendLine("\t@DrawableRes val planarSphere: Int,")
            appendLine("\t@DrawableRes val linkRope: Int")
            appendLine(") {")
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
        })
    }
}

fun String.toEnumField() =
    this.replace(Regex("[:,]"), "")
        .uppercase().replace(Regex("[ -]"), "_")