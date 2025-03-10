package com.example.gitrepoexplore.network

import com.example.gitrepoexplore.model.Repository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users/{username}/repos")
    suspend fun getRepositories(
        @Path("username") username: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): List<Repository>
}
