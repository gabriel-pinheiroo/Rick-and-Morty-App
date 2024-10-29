package com.example.rickandmorty.domain.models

data class Episode(
    val air_date: String = "",
    val characters: List<String> = emptyList(),
    val created: String = "",
    val episode: String = "",
    val id: Int = 0,
    val name: String = "",
    val url: String = ""
) {
    companion object {
        val EMPTY = Episode()
    }
}
