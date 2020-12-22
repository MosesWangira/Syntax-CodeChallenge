package com.example.syntaxcodechallenge.network.datatransferobject

import com.example.syntaxcodechallenge.database.DatabaseJson
import com.example.syntaxcodechallenge.domain.JsonResult
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkJsonContainer(val result: List<NetworkJson>)

@JsonClass(generateAdapter = true)
data class NetworkJson(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

/**
 * Convert Network results to database objects
 */
fun NetworkJsonContainer.asDomainModel(): List<JsonResult> {
    return result.map {
        JsonResult(
            body = it.body,
            id = it.id,
            title = it.title,
            userId = it.userId
        )
    }
}
/**
 * Convert Network results to database objects
 */

fun List<NetworkJson>.asDatabaseModel(): List<DatabaseJson> {
    return map {
        DatabaseJson(
            body = it.body,
            id = it.id,
            title = it.title,
            userId = it.userId
        )
    }
}