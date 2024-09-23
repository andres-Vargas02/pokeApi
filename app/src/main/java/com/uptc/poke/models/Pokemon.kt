package com.uptc.poke.models

data class Pokemon(
    val url: String,
    val name: String
) {
    fun getPokemonId(): Int {
        val segments = url.trimEnd('/').split('/')
        return segments.last().toInt()
    }
}
