package com.example.syntaxcodechallenge.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.syntaxcodechallenge.database.JsonDatabase
import com.example.syntaxcodechallenge.database.asDomainModel
import com.example.syntaxcodechallenge.domain.JsonResult
import com.example.syntaxcodechallenge.network.Network
import com.example.syntaxcodechallenge.network.datatransferobject.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


/**
 * Repository class providing a clean API for data access to the rest of the app.
 * enhances code separation and architecture
 * */

class JsonRepository(private val database: JsonDatabase) {
    val json: LiveData<List<JsonResult>> =
        Transformations.map(database.databaseDao.getJsonData()) {
            it.asDomainModel()
        }

    suspend fun refreshJsonLists() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh list is called");
            val jsonDatabase = Network.apiService.getJsonList()
            database.databaseDao.insertAll(jsonDatabase.asDatabaseModel())
        }
    }
}

