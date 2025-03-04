package com.example.rickandmorty.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.domain.models.CharacterLocationModel
import com.example.rickandmorty.domain.models.OriginModel

@Entity(tableName = "characters")
data class CharacterEntity(
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
    fun toCharacter(): Character {
        return Character(
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
            isFavorite = isFavorite
        )
    }

    companion object {
        fun fromCharacter(character: Character): CharacterEntity {
            return CharacterEntity(
                id = character.id,
                name = character.name,
                status = character.status,
                species = character.species,
                type = character.type,
                created = character.created,
                episode = character.episode,
                gender = character.gender,
                image = character.image,
                location = character.location,
                origin = character.origin,
                url = character.url,
            )
        }
    }
}
