package com.example.rickandmorty.network.dto.episodes


import com.google.gson.annotations.SerializedName

data class EpisodesResponse(
    @SerializedName("info")
    val info: Info? = null,
    @SerializedName("results")
    val results: List<Result>? = null
)