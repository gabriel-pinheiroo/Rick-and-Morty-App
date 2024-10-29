package com.example.rickandmorty.domain.models

data class Location(
    val created: String = "",
    val dimension: String = "",
    val id: Int = 0,
    val residents: List<String> = emptyList(),
    val type: String = "",
    val url: String = "",
    val name: String = ""
){
    companion object{
        val EMPTY = Location()
    }
}
