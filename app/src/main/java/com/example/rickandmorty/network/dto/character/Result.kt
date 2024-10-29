package com.example.rickandmorty.network.dto.character


import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.models.CharacterLocationModel
import com.example.rickandmorty.domain.models.OriginModel
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("episode")
    val episode: List<String>? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("location")
    val location: CharacterLocation? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin")
    val origin: Origin? = null,
    @SerializedName("species")
    val species: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null
){
    fun toCharacter() = Character(
        created = created.orEmpty(),
        episode = episode.orEmpty(),
        gender = gender.orEmpty(),
        id = id ?: 0,
        image = image.orEmpty(),
        location = location?.toCharacterLocationModel() ?: CharacterLocationModel.EMPTY,
        name = name.orEmpty(),
        origin = origin?.toOrigin()?: OriginModel.EMPTY,
        species = species.orEmpty(),
        status = status.orEmpty(),
        type = type.orEmpty(),
        url = url.orEmpty()
    )

}