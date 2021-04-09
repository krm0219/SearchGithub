package com.kurly.task.searchgithub.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val GITHUB_URL = "https://api.github.com"

    private fun getClient(baseUrl: String): Retrofit? = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val client = getClient(GITHUB_URL)?.create(RetrofitAPI::class.java)
}