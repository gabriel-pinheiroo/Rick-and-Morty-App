package com.example.rickandmorty.network.dto.episodes


import com.example.rickandmorty.domain.models.Episode
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("air_date")
    val airDate: String? = null,
    @SerializedName("characters")
    val characters: List<String>? = null,
    @SerializedName("created")
    val created: String? = null,
    @SerializedName("episode")
    val episode: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
) {
    fun toEpisode() = Episode(
        air_date = airDate.orEmpty(),
        characters = characters.orEmpty(),
        created = created.orEmpty(),
        episode = episode.orEmpty(),
        id = id ?: 0,
        name = name.orEmpty(),
        url = url.orEmpty()
    )
}