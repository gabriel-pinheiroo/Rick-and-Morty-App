package com.example.rickandmorty.features.characterDetails.navigation

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Parcelize
@Serializable
data class CharacterDetailsArgs(
    val characterId: Int = 0,
    val characterName: String = "",
) : Parcelable

val CharacterDetailsArgsType = object : NavType<CharacterDetailsArgs>(isNullableAllowed = false) {
    override fun get(
        bundle: Bundle,
        key: String,
    ): CharacterDetailsArgs? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun put(
        bundle: Bundle,
        key: String,
        value: CharacterDetailsArgs,
    ) = bundle.putString(key, Json.encodeToString(value))

    override fun parseValue(
        value: String,
    ): CharacterDetailsArgs = Json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(
        value: CharacterDetailsArgs,
    ): String = Uri.encode(Json.encodeToString(value))

    override val name: String = "CharacterDetailsArgs"
}