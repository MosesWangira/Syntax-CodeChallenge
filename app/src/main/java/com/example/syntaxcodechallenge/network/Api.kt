package com.example.syntaxcodechallenge.network

import com.example.syntaxcodechallenge.network.datatransferobject.NetworkJson
import retrofit2.http.GET

interface Api {

    /**
     * Get all requests from /post endpoint
     * */

    @GET("/posts")
    suspend fun getJsonList(): List<NetworkJson>
}