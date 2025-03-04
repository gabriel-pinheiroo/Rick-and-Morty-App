package com.example.rickandmorty.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.domain.models.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE isFavorite = 1 ")
    suspend fun getFavoriteCharacters(): List<CharacterEntity>

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteCharacterById(id: Int)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: CharacterEntity)

    @Query("UPDATE characters SET isFavorite=:isFavorite WHERE id = :id")
    suspend fun updateFavorite(isFavorite: Boolean?, id: Int)

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterByIdDao(id: Int): CharacterEntity?
}