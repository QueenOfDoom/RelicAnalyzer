package edu.shch.hsr.relicanalyzer.hsr

import androidx.annotation.StringRes
import edu.shch.hsr.relicanalyzer.R

enum class RelicSlot(@StringRes val text: Int) {
    Head(R.string.relic_head),
    Hands(R.string.relic_hands),
    Body(R.string.relic_body),
    Feet(R.string.relic_feet)
}

enum class OrnamentSlot(@StringRes val text: Int) {
    PlanarSphere(R.string.ornament_sphere),
    LinkRope(R.string.ornament_rope)
}

enum class RelicType {
    Relic,
    PlanarOrnament
}

@Suppress("unused")
enum class BodyStats {
    HP_PERCENTAGE,
    ATK_PERCENTAGE,
    DEF_PERCENTAGE,
    CRITICAL_RATE,
    CRITICAL_DMG,
    OUTGOING_HEALING,
    EFFECT_HIT_RATE
}

@Suppress("unused")
enum class FootStats {
    HP_PERCENTAGE,
    ATK_PERCENTAGE,
    DEF_PERCENTAGE,
    SPD
}

@Suppress("unused")
enum class PlanarSphereStats {
    HP_PERCENTAGE,
    ATK_PERCENTAGE,
    DEF_PERCENTAGE,
    PHYSICAL_DMG,
    FIRE_DMG,
    ICE_DMG,
    LIGHTNING_DMG,
    WIND_DMG,
    QUANTUM_DMG,
    IMAGINARY_DMG
}

@Suppress("unused")
enum class LinkRopeStats {
    HP_PERCENTAGE,
    ATK_PERCENTAGE,
    DEF_PERCENTAGE,
    BREAK_EFFECT,
    ENERGY_REGENERATION
}