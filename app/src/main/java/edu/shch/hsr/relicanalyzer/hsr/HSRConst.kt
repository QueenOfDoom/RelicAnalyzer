package edu.shch.hsr.relicanalyzer.hsr

import androidx.annotation.DrawableRes
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

@Suppress("unused")
enum class Statistic(
    @StringRes val text: Int,
    @DrawableRes val image: Int
) {
    HP(R.string.stat_hp, R.drawable.stat_hp),
    ATK(R.string.stat_atk, R.drawable.stat_atk),
    DEF(R.string.stat_def, R.drawable.stat_def),
    HP_PERCENT(R.string.stat_hp_percent, R.drawable.stat_hp),
    ATK_PERCENT(R.string.stat_atk_percent, R.drawable.stat_atk),
    DEF_PERCENT(R.string.stat_def_percent, R.drawable.stat_def),
    CRIT_RATE(R.string.stat_crit_rate, R.drawable.stat_crit_rate),
    CRIT_DMG(R.string.stat_crit_dmg, R.drawable.stat_crit_dmg),
    OUTGOING_HEALING(R.string.stat_outgoing_healing, R.drawable.stat_outgoing_heal_boost),
    EFFECT_HIT_RATE(R.string.stat_effect_hit_rate, R.drawable.stat_effect_hit_rate),
    EFFECT_RES(R.string.stat_effect_res, R.drawable.stat_effect_res),
    ENERGY_REGEN_RATE(R.string.stat_energy_regen_rate, R.drawable.stat_energy_recharge),
    BREAK_EFFECT(R.string.stat_break_effect, R.drawable.stat_break_effect),
    SPEED(R.string.stat_speed, R.drawable.stat_speed),

    PHYSICAL_DAMAGE(R.string.stat_dmg_physical, R.drawable.stat_damage_physical),
    FIRE_DAMAGE(R.string.stat_dmg_fire, R.drawable.stat_damage_fire),
    ICE_DAMAGE(R.string.stat_dmg_ice, R.drawable.stat_damage_ice),
    LIGHTNING_DAMAGE(R.string.stat_dmg_lightning, R.drawable.stat_damage_lightning),
    WIND_DAMAGE(R.string.stat_dmg_wind, R.drawable.stat_damage_wind),
    QUANTUM_DAMAGE(R.string.stat_dmg_quantum, R.drawable.stat_damage_quantum),
    IMAGINARY_DAMAGE(R.string.stat_dmg_imaginary, R.drawable.stat_damage_imaginary);

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

@Suppress("unused")
val BodyMainStats = listOf(
    Statistic.HP_PERCENT,
    Statistic.ATK_PERCENT,
    Statistic.DEF_PERCENT,
    Statistic.CRIT_RATE,
    Statistic.CRIT_DMG,
    Statistic.OUTGOING_HEALING,
    Statistic.EFFECT_HIT_RATE
)

@Suppress("unused")
val FootMainStats = listOf(
    Statistic.HP_PERCENT,
    Statistic.ATK_PERCENT,
    Statistic.DEF_PERCENT,
    Statistic.SPEED
)

@Suppress("unused")
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

@Suppress("unused")
val LinkRopeMainStats = listOf(
    Statistic.HP_PERCENT,
    Statistic.ATK_PERCENT,
    Statistic.DEF_PERCENT,
    Statistic.BREAK_EFFECT,
    Statistic.ENERGY_REGEN_RATE
)

@Suppress("unused")
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

@Suppress("unused")
enum class Element(
    @StringRes val text: Int,
    @DrawableRes val image: Int,
) {
    PHYSICAL(R.string.element_physical, R.drawable.element_physical),
    FIRE(R.string.element_fire, R.drawable.element_fire),
    ICE(R.string.element_ice, R.drawable.element_ice),
    LIGHTNING(R.string.element_lightning, R.drawable.element_lightning),
    WIND(R.string.element_wind, R.drawable.element_wind),
    QUANTUM(R.string.element_quantum, R.drawable.element_quantum),
    IMAGINARY(R.string.element_imaginary, R.drawable.element_imaginary);
}

@Suppress("unused")
enum class Path(
    @StringRes val text: Int,
    @DrawableRes val image: Int,
) {
    DESTRUCTION(R.string.path_destruction, R.drawable.path_destruction),
    HUNT(R.string.path_hunt, R.drawable.path_hunt),
    ERUDITION(R.string.path_erudition, R.drawable.path_erudition),
    HARMONY(R.string.path_harmony, R.drawable.path_harmony),
    NIHILITY(R.string.path_nihility, R.drawable.path_nihility),
    PRESERVATION(R.string.path_preservation, R.drawable.path_preservation),
    ABUNDANCE(R.string.path_abundance, R.drawable.path_abundance)
}

enum class Rarity(val numeric: Int) {
    SUPER_RARE(5),
    RARE(4),
    COMMON(3);

    companion object {
        fun fromId(id: Int) = when(id) {
            SUPER_RARE.numeric -> SUPER_RARE
            RARE.numeric -> RARE
            COMMON.numeric -> COMMON
            else -> throw IllegalArgumentException("Illegal Rarity ID: $id")
        }
    }
}