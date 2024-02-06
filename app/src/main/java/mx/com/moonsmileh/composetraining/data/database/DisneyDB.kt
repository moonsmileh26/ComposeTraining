package mx.com.moonsmileh.composetraining.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1)
abstract class DisneyDB : RoomDatabase() {
    abstract val dao: CharacterDAO
}