package edu.shch.hsr.relicanalyzer.util

import androidx.annotation.StringRes

open class RouteItem(val type: RouteType)

data class EnumRouteItem<T>(val value: T):
    RouteItem(RouteType.ENUM_ID)

data class LocaleRouteItem(@StringRes val id: Int):
    RouteItem(RouteType.LOCALE_ID)

enum class RouteType {
    LOCALE_ID,
    ENUM_ID
}