package edu.shch.hsr.relicanalyzer.util

fun <X, Y> Pair<X, Y>.reverse(): Pair<Y, X> {
    return (this.second to this.first)
}

fun <X> Pair<X, X>.reverseOn(condition: Boolean): Pair<X, X> {
    return if (condition) this.reverse() else this
}