package edu.shch.hsr.relicanalyzer.util

import androidx.annotation.StringRes
import edu.shch.hsr.relicanalyzer.R

open class RouteItem(val type: RouteType)

data class EnumRouteItem<T>(val value: T):
    RouteItem(RouteType.ENUM_ID)

data class LocaleRouteItem(@StringRes val id: Int): RouteItem(RouteType.LOCALE_ID) {
    override fun equals(other: Any?) = other is LocaleRouteItem && this.id == other.id
    override fun hashCode() = id

    companion object {
        val DoNotTouch = LocaleRouteItem(R.string.ui_do_not_touch)
        val Character = LocaleRouteItem(R.string.character)
        val LightCone = LocaleRouteItem(R.string.lightcone)
        val Relic = LocaleRouteItem(R.string.relic)
        val Ornament = LocaleRouteItem(R.string.ornament)
    }
}

enum class RouteType {
    LOCALE_ID,
    ENUM_ID
}