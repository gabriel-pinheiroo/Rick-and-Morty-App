package com.example.rickandmorty.network.dto.character


import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("info")
    val info: Info? = null,
    @SerializedName("results")
    val results: List<CharacterApi>? = null
)