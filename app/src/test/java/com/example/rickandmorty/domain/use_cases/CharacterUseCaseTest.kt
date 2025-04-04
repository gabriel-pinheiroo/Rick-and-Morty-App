package com.example.rickandmorty.domain.use_cases

import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import com.example.rickandmorty.domain.models.Character
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.kotlin.any

class CharacterUseCaseTest{

    private lateinit var useCase: CharacterUseCase
    private val repository: CharacterRepository = mock(CharacterRepository::class.java)

    @Before
    fun setup() {
        useCase = CharacterUseCase(repository)
    }

    @Test
    fun `getCharacters should return success when repository call is successful`() = runBlocking {
        val mockCharacter = Character(id = 1, name = "Rick Sanchez")
        `when`(repository.getCharacters(1)).thenReturn(Result.success(listOf(mockCharacter)))

        val result = useCase.getCharacters(1)
        assertTrue(result.isSuccess)
        assertEquals(listOf(mockCharacter), result.getOrNull())
    }

    @Test
    fun `getCharacterById should return success when repository call is successful`() = runBlocking {
        val mockCharacter = Character(id = 1, name = "Rick Sanchez")
        `when`(repository.getCharacterById(1)).thenReturn(Result.success(mockCharacter))

        val result = useCase.getCharacterById(1)
        assertTrue(result.isSuccess)
        assertEquals(mockCharacter, result.getOrNull())
    }

    @Test
    fun `insertCharacter should return success when repository call is successful`() = runBlocking {
        val mockCharacter = Character(id = 1, name = "Rick Sanchez")
        `when`(repository.insertCharacter(mockCharacter)).thenReturn(Result.success(mockCharacter))

        val result = useCase.insertCharacter(mockCharacter)
        assertTrue(result.isSuccess)
        assertEquals(mockCharacter, result.getOrNull())
    }

    @Test
    fun `deleteFavoriteCharacter should return success when repository call is successful`() = runBlocking {
        `when`(repository.deleteFavoriteCharacter(1)).thenReturn(Result.success(Character.EMPTY))

        val result = useCase.deleteFavoriteCharacter(1)
        assertTrue(result.isSuccess)
        assertEquals(Character.EMPTY, result.getOrNull())
    }

    @Test
    fun `getCharacterByIdDao should return success when repository call is successful`() = runBlocking {
        val mockCharacter = Character(id = 1, name = "Rick Sanchez")
        `when`(repository.getCharacterByIdDao(1)).thenReturn(Result.success(mockCharacter))

        val result = useCase.getCharacterByIdDao(1)
        assertTrue(result.isSuccess)
        assertEquals(mockCharacter, result.getOrNull())
    }

    @Test
    fun `updateFavorite should return success when repository call is successful`() = runBlocking {
        val mockCharacter = Character(id = 1, name = "Rick Sanchez", isFavorite = true)
        `when`(repository.updateFavorite(isFavorite = mockCharacter.isFavorite, id = mockCharacter.id))
            .thenReturn(Result.success(Character.EMPTY))

        val result = useCase.updateFavorite(mockCharacter)
        assertTrue(result.isSuccess)
        assertEquals(Character.EMPTY, result.getOrNull())
    }

    @Test
    fun `getAllFavoriteCharacters should return success when repository call is successful`() = runBlocking {
        val mockCharacter = Character(id = 1, name = "Rick Sanchez")
        `when`(repository.getAllFavoriteCharacters()).thenReturn(Result.success(listOf(mockCharacter)))

        val result = useCase.getAllFavoriteCharacters()
        assertTrue(result.isSuccess)
        assertEquals(listOf(mockCharacter), result.getOrNull())
    }

    @Test
    fun `getCharacters should return failure when repository call fails`() = runBlocking {
        val exception = RuntimeException("API error")
        `when`(repository.getCharacters(1)).thenReturn(Result.failure(exception))

        val result = useCase.getCharacters(1)
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `getCharacterById should return failure when repository call fails`() = runBlocking {
        val exception = RuntimeException("Character not found")
        `when`(repository.getCharacterById(1)).thenReturn(Result.failure(exception))

        val result = useCase.getCharacterById(1)
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `insertCharacter should return failure when repository call fails`() = runBlocking {
        val exception = RuntimeException("Database insert failed")
        `when`(repository.insertCharacter(any())).thenReturn(Result.failure(exception))

        val result = useCase.insertCharacter(Character(id = 1, name = "Rick"))
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `deleteFavoriteCharacter should return failure when repository call fails`() = runBlocking {
        val exception = RuntimeException("Delete operation failed")
        `when`(repository.deleteFavoriteCharacter(1)).thenReturn(Result.failure(exception))

        val result = useCase.deleteFavoriteCharacter(1)
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `getCharacterByIdDao should return failure when repository call fails`() = runBlocking {
        val exception = RuntimeException("Database error")
        `when`(repository.getCharacterByIdDao(1)).thenReturn(Result.failure(exception))

        val result = useCase.getCharacterByIdDao(1)
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `updateFavorite should return failure when repository call fails`() = runBlocking {
        val exception = RuntimeException("Failed to update favorite")
        `when`(repository.updateFavorite(anyBoolean(), anyInt())).thenReturn(Result.failure(exception))

        val result = useCase.updateFavorite(Character(id = 1, name = "Rick", isFavorite = true))
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `getAllFavoriteCharacters should return failure when repository call fails`() = runBlocking {
        val exception = RuntimeException("Database query failed")
        `when`(repository.getAllFavoriteCharacters()).thenReturn(Result.failure(exception))

        val result = useCase.getAllFavoriteCharacters()
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
