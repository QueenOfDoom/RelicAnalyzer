import java.net.URL

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

tasks {
    register("scrapeResources") {
        group = "update"

        val discoveryUrl = URL("https://www.mobilemeta.gg/honkai-starrail/database/relic")
        val resourceBaseUrl = "https://images.mobilemeta.gg/starrail/static/icon/relic"
        val data = String(discoveryUrl.readBytes())

        @Suppress("SpellCheckingInspection")
        val labelRegex = Regex("<div class=\"font-starrailweb text-yellow-400\">([^<]+)</div>")
        val labels = labelRegex.findAll(data).map { match -> match.groupValues[1] }.toList()

        val imgRegex = Regex("/static/icon/relic/([13]\\d{2}).png")
        val imageCodes = imgRegex.findAll(data).map { match -> match.groupValues[1] }.toList()

        assert(labels.size == imageCodes.size)

        val generatedCodeFile = File("./app/src/main/java/edu/shch/hsr/relicanalyzer/hsr/HSR.kt")
        val generatedRelicEnum = StringBuilder()
        val generatedOrnamentEnum = StringBuilder()
        val labelFile = File("./app/src/main/res/values/dynamic-relics.xml")

        val labelResource = StringBuilder().appendLine("<resources>")
        for ((imageCode, label) in imageCodes.zip(labels)) {
            if (imageCode.startsWith("1")) {
                labelResource.appendLine("\t<string name=\"relic.${imageCode}\">${label}</string>")
                val enumLabel = label.replace(Regex("[:,]"), "")
                    .uppercase().replace(Regex("[ -]"), "_")
                generatedRelicEnum.append("\t$enumLabel(R.string.relic_$imageCode, ")
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
        })
    }
}