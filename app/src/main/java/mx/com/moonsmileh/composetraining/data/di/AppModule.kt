package mx.com.moonsmileh.composetraining.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.com.moonsmileh.composetraining.data.database.CharacterDAO
import mx.com.moonsmileh.composetraining.data.database.DisneyDB
import mx.com.moonsmileh.composetraining.data.network.DisneyApiService
import mx.com.moonsmileh.composetraining.data.network.DisneyRepository
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
    fun providesRepository(apiService: DisneyApiService, dao: CharacterDAO): DisneyRepository {
        return DisneyRepository(apiService, dao)
    }


    @Provides
    @Singleton
    fun providesDao(database: DisneyDB): CharacterDAO {
        return database.dao
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): DisneyDB {
        return Room.databaseBuilder(appContext, DisneyDB::class.java, "DISNEY_DB").build()
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