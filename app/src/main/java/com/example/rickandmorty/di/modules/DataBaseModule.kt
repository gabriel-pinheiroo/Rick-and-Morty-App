package com.example.rickandmorty.di.modules

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.database.CharacterDao
import com.example.rickandmorty.database.CharacterDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext context: Context): CharacterDataBase {
        return Room.databaseBuilder(
            context,
            CharacterDataBase::class.java,
            "character.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(characterDatabase: CharacterDataBase): CharacterDao =
        characterDatabase.getCharacterDao()
}