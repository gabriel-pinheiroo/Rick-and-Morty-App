package com.example.rickandmorty.domain.models

data class Character(
    val created: String = "",
    val episode: List<String> = emptyList(),
    val gender: String = "",
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
