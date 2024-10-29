package com.example.rickandmorty.network.dto.character


import com.example.rickandmorty.domain.models.CharacterLocationModel
import com.google.gson.annotations.SerializedName

data class CharacterLocation(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
){
    fun toCharacterLocationModel() = CharacterLocationModel(
        name = name.orEmpty(),
        url = url.orEmpty()
    )
}