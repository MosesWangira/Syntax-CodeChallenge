package com.example.syntaxcodechallenge.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.syntaxcodechallenge.database.getDatabase
import com.example.syntaxcodechallenge.repositories.JsonRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to refresh list
     */
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)

        val repository = JsonRepository(database)

        return try {
            repository.refreshJsonLists()

            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}