package com.LCDP.marvelwiki

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersAPI {
    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int,
        @Query("apikey") apiKey: String = Constant.API_KEY,
        @Query("ts") timestamp: String = Constant.ts,
        @Query("hash") hash: String = Constant.hash()
    ): Response<CharacterResponse>
}