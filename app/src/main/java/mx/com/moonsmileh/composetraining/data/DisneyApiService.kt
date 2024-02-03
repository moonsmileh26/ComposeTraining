package mx.com.moonsmileh.composetraining.data

import mx.com.moonsmileh.composetraining.data.response.DisneyResponse
import retrofit2.http.GET

interface DisneyApiService {

    @GET("character")
    suspend fun getCharacters(): DisneyResponse
}