package com.LCDP.marvelwiki

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val marvelAPI: CharactersAPI by lazy {
        retrofit.create(CharactersAPI::class.java)
    }
}