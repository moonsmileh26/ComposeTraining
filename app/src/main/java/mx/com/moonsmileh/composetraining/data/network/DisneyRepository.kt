package mx.com.moonsmileh.composetraining.data.network

import mx.com.moonsmileh.composetraining.data.database.CharacterDAO
import mx.com.moonsmileh.composetraining.data.database.CharacterEntity
import mx.com.moonsmileh.composetraining.data.network.response.Data
import mx.com.moonsmileh.composetraining.data.model.DisneyCharacter

class DisneyRepository(private val apiService: DisneyApiService, private val dao: CharacterDAO) {

    suspend fun getCharactersFromDB(): List<DisneyCharacter> {
        val entities = dao.getAllCharacters()
        return entities.map {
            DisneyCharacter(it.name, it.imageUrl)
        }
    }

    suspend fun insertCharacterIntoDB(disneyCharacters: List<DisneyCharacter>) {
        val characterEntities = disneyCharacters.map {
            CharacterEntity(name = it.name, imageUrl = it.image ?: "")
        }
        dao.insertAllCharacters(characterEntities)
    }

    suspend fun getCharactersFromAPI(): Result<List<DisneyCharacter>> {
        return try {
            val response = apiService.getCharacters().data
            val characters = response.map { converter(it) }
            Result.success(characters)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    private fun converter(data: Data): DisneyCharacter {
        return DisneyCharacter(data.name, data.imageUrl)
    }
}