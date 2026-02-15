package com.hit.aliasgameapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomWordApi {
    // Make sure 'suspend' is here and the name is 'getGameWords'
    @GET("word")
    suspend fun getGameWords(
        @Query("number") count: Int
    ): Response<List<String>>
}