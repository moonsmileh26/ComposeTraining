package mx.com.moonsmileh.composetraining.data.network

import mx.com.moonsmileh.composetraining.data.network.response.DisneyResponse
import retrofit2.http.GET

interface DisneyApiService {

    @GET("character")
    suspend fun getCharacters(): DisneyResponse
}