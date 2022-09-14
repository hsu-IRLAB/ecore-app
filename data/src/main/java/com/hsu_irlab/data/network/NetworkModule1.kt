package com.hsu_irlab.data.network

import com.hsu_irlab.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object NetworkModule1 {
    private const val BASE_URL = BuildConfig.BASE_URL
    private val okHttpClient = OkHttpClient.Builder().
    addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).addInterceptor {
        // Request
        val request = it.request()
            .newBuilder()
            .addHeader("jwt_token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVU0VSX0lEIjo4NiwiTkFNRSI6Im5hbmFuYW1lIiwiaWF0IjoxNjYwMTExODc1LCJleHAiOjE2ODYwMzE4NzUsImlzcyI6IkVDT1JFIn0.1lhRXwjfir76goRLAJOJlHdxzBbquQzaAwq7WvsCJQY")
            .build()
        // Response
        val response = it.proceed(request)
        response
    }.build()

    private val getRetrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getRetrofitService : ApiInterface by lazy{
        getRetrofit.create(ApiInterface::class.java)
    }
}