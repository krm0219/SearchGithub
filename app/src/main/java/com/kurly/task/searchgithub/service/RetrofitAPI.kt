package com.kurly.task.searchgithub.service

import com.kurly.task.searchgithub.model.RepositoriesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    /*  https://api.github.com/search/repositories */
    @GET("search/repositories")
    suspend fun getRepositories(@Query("q") query: String): Response<RepositoriesModel>

}