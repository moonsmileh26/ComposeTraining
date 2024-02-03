package mx.com.moonsmileh.composetraining

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.moonsmileh.composetraining.data.DisneyApiService
import mx.com.moonsmileh.composetraining.data.DisneyRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRepository(apiService: DisneyApiService): DisneyRepository {
        return DisneyRepository(apiService)
    }

    @Provides
    @Singleton
    fun providesApiService(okHttpClient: OkHttpClient): DisneyApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.disneyapi.dev/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(DisneyApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

}