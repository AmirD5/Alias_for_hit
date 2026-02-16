package com.hit.aliasgameapp.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ExtraApis {
}
interface NameApi {
    @GET("api")
    suspend fun getRandomName(): Response<RandomUserResponse>
}

data class RandomUserResponse(val results: List<UserResult>)
data class UserResult(val name: UserName)
data class UserName(val first: String, val last: String)
interface ImageApi {
    @GET
    suspend fun downloadImage(@Url url: String): Response<ResponseBody>
}