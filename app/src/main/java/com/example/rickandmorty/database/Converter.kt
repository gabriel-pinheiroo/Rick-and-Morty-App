package com.example.rickandmorty.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.rickandmorty.domain.models.CharacterLocationModel
import com.example.rickandmorty.domain.models.OriginModel

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromCharacterLocationModel(value: CharacterLocationModel): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCharacterLocationModel(value: String): CharacterLocationModel {
        return gson.fromJson(value, CharacterLocationModel::class.java)
    }

    @TypeConverter
    fun fromOriginModel(value: OriginModel): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toOriginModel(value: String): OriginModel {
        return gson.fromJson(value, OriginModel::class.java)
    }
}
