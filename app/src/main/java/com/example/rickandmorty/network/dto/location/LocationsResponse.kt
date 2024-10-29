package com.example.rickandmorty.network.dto.location


import com.google.gson.annotations.SerializedName

data class  LocationsResponse(
    @SerializedName("info")
    val info: Info? = null,
    @SerializedName("results")
    val results: List<Result>? = null
)