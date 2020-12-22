package com.example.syntaxcodechallenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    /**
     * Base URL to get data from
     * */
    private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: Api by lazy {
        retrofit
            .build()
            .create(Api::class.java)
    }
}