package com.example.rickandmorty.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    val created: String = "",
    val episode: List<String> = emptyList(),
    val gender: String = "",
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val image: String = "",
    val location: CharacterLocationModel = CharacterLocationModel.EMPTY,
    val name: String = "",
    val origin: OriginModel = OriginModel.EMPTY,
    val url: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val isFavorite: Boolean = false
) {
    companion object {
        val EMPTY = Character()
    }

    fun Character.toEntity(): CharacterEntity {
        return CharacterEntity(
            created = created,
            episode = episode,
            gender = gender,
            id = id,
            image = image,
            location = location,
            name = name,
            origin = origin,
            url = url,
            status = status,
            species = species,
            type = type,
        )
    }
}

data class CharacterLocationModel(
    val name: String = "",
    val url: String = ""
) {
    companion object {
        val EMPTY = CharacterLocationModel()
    }
}

data class OriginModel(
    val name: String = "",
    val url: String = ""
) {
    companion object {
        val EMPTY = OriginModel()
    }
}
