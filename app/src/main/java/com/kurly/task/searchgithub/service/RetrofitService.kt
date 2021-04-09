package com.kurly.task.searchgithub.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val GITHUB_URL = "https://api.github.com"

    private fun getClient(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val client = getClient(GITHUB_URL).create(RetrofitAPI::class.java)
}