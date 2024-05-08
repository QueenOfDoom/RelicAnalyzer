package edu.shch.hsr.relicanalyzer.hsr

import androidx.annotation.StringRes
import edu.shch.hsr.relicanalyzer.R

@Suppress("unused")
enum class RelicSlot(@StringRes val text: Int) {
    Head(R.string.relic_head),
    Hands(R.string.relic_hands),
    Body(R.string.relic_body),
    Feet(R.string.relic_feet)
}

@Suppress("unused")
enum class OrnamentSlot(@StringRes val text: Int) {
    PlanarSphere(R.string.ornament_sphere),
    LinkRope(R.string.ornament_rope)
}

enum class RelicType {
    Relic,
    PlanarOrnament
}

interface GenericRelic

enum class Statistic(
    @StringRes val text: Int
) {
    HP(R.string.stat_hp),
    ATK(R.string.stat_atk),
    DEF(R.string.stat_def),
    HP_PERCENT(R.string.stat_hp_percent),
    ATK_PERCENT(R.string.stat_atk_percent),
    DEF_PERCENT(R.string.stat_def_percent),
    CRIT_RATE(R.string.stat_crit_rate),
    CRIT_DMG(R.string.stat_crit_dmg),
    OUTGOING_HEALING(R.string.stat_outgoing_healing),
    EFFECT_HIT_RATE(R.string.stat_effect_hit_rate),
    EFFECT_RES(R.string.stat_effect_res),
    ENERGY_REGEN_RATE(R.string.stat_energy_regen_rate),
    BREAK_EFFECT(R.string.stat_break_effect),
    SPEED(R.string.stat_speed),

    PHYSICAL_DAMAGE(R.string.stat_dmg_physical),
    FIRE_DAMAGE(R.string.stat_dmg_fire),
    ICE_DAMAGE(R.string.stat_dmg_ice),
    LIGHTNING_DAMAGE(R.string.stat_dmg_lightning),
    WIND_DAMAGE(R.string.stat_dmg_wind),
    QUANTUM_DAMAGE(R.string.stat_dmg_quantum),
    IMAGINARY_DAMAGE(R.string.stat_dmg_imaginary);

    companion object {
        /**
         * Redundant Function, so that I can convert
         * textual information into structured information.
         *
         * Copies the string-values from the `hsr.xml`
         * resource file.
         */
        fun fromString(stat: String) = when(stat) {
            "HP" -> HP
            "ATK" -> ATK
            "DEF" -> DEF
            "HP%" -> HP_PERCENT
            "ATK%" -> ATK_PERCENT
            "DEF%" -> DEF_PERCENT
            "CRIT Rate" -> CRIT_RATE
            "CRIT DMG" -> CRIT_DMG
            "Outgoing Healing" -> OUTGOING_HEALING
            "Effect Hit Rate" -> EFFECT_HIT_RATE
            "Effect RES" -> EFFECT_RES
            "Energy Regen Rate" -> ENERGY_REGEN_RATE
            "Break Effect" -> BREAK_EFFECT
            "Speed" -> SPEED
            "Physical DMG" -> PHYSICAL_DAMAGE
            "Fire DMG" -> FIRE_DAMAGE
            "Ice DMG" -> ICE_DAMAGE
            "Lightning DMG" -> LIGHTNING_DAMAGE
            "Wind DMG" -> WIND_DAMAGE
            "Quantum DMG" -> QUANTUM_DAMAGE
            "Imaginary DMG" -> IMAGINARY_DAMAGE
            else -> throw IllegalArgumentException("Unknown Stat: $stat")
        }
    }
}

val BodyMainStats = listOf(
    Statistic.HP_PERCENT,
    Statistic.ATK_PERCENT,
    Statistic.DEF_PERCENT,
    Statistic.CRIT_RATE,
    Statistic.CRIT_DMG,
    Statistic.OUTGOING_HEALING,
    Statistic.EFFECT_HIT_RATE
)

val FootMainStats = listOf(
    Statistic.HP_PERCENT,
    Statistic.ATK_PERCENT,
    Statistic.DEF_PERCENT,
    Statistic.SPEED
)

val PlanarSphereMainStats = listOf(
    Statistic.HP_PERCENT,
    Statistic.ATK_PERCENT,
    Statistic.DEF_PERCENT,
    Statistic.PHYSICAL_DAMAGE,
    Statistic.FIRE_DAMAGE,
    Statistic.ICE_DAMAGE,
    Statistic.LIGHTNING_DAMAGE,
    Statistic.WIND_DAMAGE,
    Statistic.QUANTUM_DAMAGE,
    Statistic.IMAGINARY_DAMAGE
)

val LinkRopeMainStats = listOf(
    Statistic.HP_PERCENT,
    Statistic.ATK_PERCENT,
    Statistic.DEF_PERCENT,
    Statistic.BREAK_EFFECT,
    Statistic.ENERGY_REGEN_RATE
)

val SubStats = listOf(
    Statistic.HP,
    Statistic.ATK,
    Statistic.DEF,
    Statistic.HP_PERCENT,
    Statistic.ATK_PERCENT,
    Statistic.DEF_PERCENT,
    Statistic.CRIT_RATE,
    Statistic.CRIT_DMG,
    Statistic.EFFECT_HIT_RATE,
    Statistic.EFFECT_RES,
    Statistic.BREAK_EFFECT,
    Statistic.SPEED
)

enum class Element(
    @StringRes val text: Int,
) {
    PHYSICAL(R.string.element_physical),
    FIRE(R.string.element_fire),
    ICE(R.string.element_ice),
    LIGHTNING(R.string.element_lightning),
    WIND(R.string.element_wind),
    QUANTUM(R.string.element_quantum),
    IMAGINARY(R.string.element_imaginary);

    companion object {
        /**
         * Redundant Function, so that I can convert
         * textual information into structured information.
         *
         * Copies the string-values from the `hsr.xml`
         * resource file.
         */
        fun fromString(element: String) = when(element) {
            "Physical" -> PHYSICAL
            "Fire" -> FIRE
            "Ice" -> ICE
            "Lightning" -> LIGHTNING
            "Wind" -> WIND
            "Quantum" -> QUANTUM
            "Imaginary" -> IMAGINARY
            else -> throw IllegalArgumentException("Unknown Element: $element")
        }
    }
}