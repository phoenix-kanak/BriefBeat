package com.example.briefbeat

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitAPI {
    @GET
    fun getAllNews(@Url url: String?): Call<NewsModal?>?

    @GET
    fun getNewsByCategory(@Url url: String?): Call<NewsModal?>?

    // Add a relative path for the base URL
    companion object {
        const val BASE_URL = "https://newsapi.org/"
    }
}
