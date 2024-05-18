package com.bangkitacademy.githubuserapp.core.data.source.remote.retrofit

import com.bangkitacademy.githubuserapp.core.data.source.remote.response.DetailUserResponse
import com.bangkitacademy.githubuserapp.core.data.source.remote.response.GithubResponse
import com.bangkitacademy.githubuserapp.core.data.source.remote.response.ItemsItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    suspend fun getGithub(
        @Query("q") q: String
    ): GithubResponse

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): DetailUserResponse

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String
    ): List<ItemsItem>

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String
    ): List<ItemsItem>
}