package com.example.syntaxcodechallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DatabaseJson::class],
    version = 1,
    exportSchema = false
)
abstract class JsonDatabase : RoomDatabase() {
    abstract val databaseDao: DatabaseDao
}


private lateinit var INSTANCE: JsonDatabase

fun getDatabase(context: Context): JsonDatabase {
    synchronized(JsonDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                JsonDatabase::class.java,
                "json_result_db"
            ).build()
        }
    }
    return INSTANCE
}