package edu.shch.hsr.relicanalyzer.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrawableResource(@DrawableRes val id: Int)
data class StringResource(@StringRes val id: Int)

fun Int.asDrawableRes() = DrawableResource(this)
fun Int.asStringRes() = StringResource(this)