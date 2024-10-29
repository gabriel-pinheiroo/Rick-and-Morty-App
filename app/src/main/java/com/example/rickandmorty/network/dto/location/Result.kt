package com.example.rickandmorty.network.dto.location


import com.example.rickandmorty.domain.models.Location
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("dimension")
    val dimension: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("residents")
    val residents: List<String>? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null
){
    fun toLocation() = Location(
        created = created.orEmpty(),
        dimension = dimension.orEmpty(),
        id = id ?: 0,
        name = name.orEmpty(),
        residents = residents.orEmpty(),
        type = type.orEmpty(),
        url = url.orEmpty()
    )
}