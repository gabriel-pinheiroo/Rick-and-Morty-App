package com.example.rickandmorty.network.repository

import com.example.rickandmorty.database.CharacterDao
import com.example.rickandmorty.database.CharacterEntity
import com.example.rickandmorty.domain.models.Character
import com.example.rickandmorty.network.dto.character.CharacterApi
import com.example.rickandmorty.network.dto.character.CharactersResponse
import com.example.rickandmorty.network.service.CharacterService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {

    @Mock
    private lateinit var api: CharacterService

    @Mock
    private lateinit var characterDao: CharacterDao

    @Mock
    private lateinit var repository: CharacterRepositoryImpl

    @Before
    fun setup() {
        repository = CharacterRepositoryImpl(api, characterDao)
    }

    @Test
    fun `getCharacters should return success when API call is successful`() = runBlocking {
        val mockCharacter = CharactersResponse(info = null, results = listOf(CharacterApi(id = 1, name = "Rick Sanchez")))
        `when`(api.getCharacters(1)).thenReturn(mockCharacter)

        val result = repository.getCharacters(1)
        assertTrue(result.isSuccess)
        val expectedResponse = mockCharacter.results?.map { it.toCharacter() } ?: emptyList()
        assertEquals(expectedResponse, result.getOrNull())
    }

    @Test
    fun `getCharacters should return failure when API call fails`() = runBlocking {
        `when`(api.getCharacters(1)).thenThrow(HttpException::class.java)

        val result = repository.getCharacters(1)
        assertTrue(result.isFailure)
    }

    @Test
    fun `getCharacterById should return success when API call is successful`() = runBlocking {
        val mockCharacter = CharacterApi(id = 1, name = "Rick Sanchez")
        `when`(api.getCharacterById(1)).thenReturn(mockCharacter)

        val result = repository.getCharacterById(1)
        assertTrue(result.isSuccess)
        assertEquals(mockCharacter.toCharacter(), result.getOrNull())
    }

    @Test
    fun `insertCharacter should return success when database operation succeeds`() = runBlocking {
        val character = Character(id = 1, name = "Rick Sanchez")
        val entity = CharacterEntity.fromCharacter(character)
        `when`(characterDao.insertCharacters(entity)).thenReturn(Unit)

        val result = repository.insertCharacter(character)
        assertTrue(result.isSuccess)
        assertEquals(character, result.getOrNull())
    }

    @Test
    fun `getCharacterByIdDao should return success when character exists`() = runBlocking {
        val mockEntity = CharacterEntity(id = 1, name = "Rick Sanchez")
        `when`(characterDao.getCharacterByIdDao(1)).thenReturn(mockEntity)

        val result = repository.getCharacterByIdDao(1)
        assertTrue(result.isSuccess)
        assertEquals(mockEntity.toCharacter(), result.getOrNull())
    }

    @Test
    fun `updateFavorite should return success when update succeeds`() = runBlocking {
        `when`(characterDao.updateFavorite(true, 1)).thenReturn(Unit)

        val result = repository.updateFavorite(true, 1)
        assertTrue(result.isSuccess)
    }

    @Test
    fun `getAllFavoriteCharacters should return success when data is available`() = runBlocking {
        val mockEntities = listOf(CharacterEntity(id = 1, name = "Rick Sanchez"))
        `when`(characterDao.getAllCharacters()).thenReturn(mockEntities)

        val result = repository.getAllFavoriteCharacters()
        assertTrue(result.isSuccess)
        assertEquals(mockEntities.map { it.toCharacter() }, result.getOrNull())
    }
}
