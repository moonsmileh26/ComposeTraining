package mx.com.moonsmileh.composetraining.data

import mx.com.moonsmileh.composetraining.data.response.Data
import mx.com.moonsmileh.composetraining.model.DisneyCharacter

class DisneyRepository(private val apiService: DisneyApiService) {

    suspend fun getCharacters(): Result<List<DisneyCharacter>> {
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