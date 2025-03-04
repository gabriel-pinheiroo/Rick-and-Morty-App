package com.example.rickandmorty.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(version = 1, entities = [CharacterEntity::class], exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharacterDataBase : RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}