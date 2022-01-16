package com.bimabagaskhoro.asigmentkompas.data.source.remote.network

import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemRepos
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ItemsDetail
import com.bimabagaskhoro.asigmentkompas.data.source.remote.response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    //Search User
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<ResponseUser>

    //Detail User
    @GET("users/{username}")
    @Headers("Authorization: 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<ItemsDetail>

    //Get Repository
    @GET("users/{username}/repos")
    @Headers("Authorization: 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")
    fun getUserRepository(
        @Path("username") username: String
    ): Call<List<ItemRepos>>

}