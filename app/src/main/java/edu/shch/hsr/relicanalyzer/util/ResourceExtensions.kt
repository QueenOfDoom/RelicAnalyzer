package edu.shch.hsr.relicanalyzer.util

import androidx.annotation.DrawableRes

data class DrawableResource(@DrawableRes val id: Int)

fun Int.asDrawableRes() = DrawableResource(this)