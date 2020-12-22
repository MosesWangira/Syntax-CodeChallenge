package com.example.syntaxcodechallenge.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DatabaseDao {
    /**
     * Query all list of items from room database
     * */
    @Query("select * from json_table")
    fun getJsonData(): LiveData<List<DatabaseJson>>

    /**
     * Insert all items to database
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(jsonData: List<DatabaseJson>)
}