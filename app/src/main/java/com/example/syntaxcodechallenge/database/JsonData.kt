package com.example.syntaxcodechallenge.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.syntaxcodechallenge.domain.JsonResult
/**
 * DatabaseJson represents an item entity in the database.
 */
@Entity(tableName = "json_table")
data class DatabaseJson constructor(
    @PrimaryKey
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)



/**
 * Map DatabaseJson to domain entities
 */
fun List<DatabaseJson>.asDomainModel(): List<JsonResult> {
    return map {
        JsonResult(
            body = it.body,
            id = it.id,
            title = it.title,
            userId = it.userId
        )
    }
}