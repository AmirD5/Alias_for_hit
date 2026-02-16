package com.hit.aliasgameapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomWordApi {
    @GET("random/noun")
    suspend fun getGameWords(
        @Query("count") count: Int
    ): Response<List<String>>
}