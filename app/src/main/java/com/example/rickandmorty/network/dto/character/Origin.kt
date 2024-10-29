package com.example.rickandmorty.network.dto.character


import com.example.rickandmorty.domain.models.OriginModel
import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
){
    fun toOrigin() = OriginModel(
        name = name.orEmpty(),
        url = url.orEmpty()
    )
}