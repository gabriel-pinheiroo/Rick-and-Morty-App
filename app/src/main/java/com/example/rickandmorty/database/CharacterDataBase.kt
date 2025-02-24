package com.example.rickandmorty.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.domain.models.Character

@Database(version = 1, entities = [Character::class], exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterDataBase : RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}