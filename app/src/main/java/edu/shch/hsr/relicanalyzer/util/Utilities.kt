package edu.shch.hsr.relicanalyzer.util

fun <T> coalesce(vararg objects: T?, fallback: T): T {
    for (`object` in objects) {
        if (`object` != null) {
            return `object`
        }
    }
    return fallback
}