package com.hsu_irlab.data.network

import com.hsu_irlab.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object NetworkModule {
    private const val TEST_URL = BuildConfig.BASE_URL
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).addInterceptor {
        // Request
        val request = it.request()
            .newBuilder()
            .addHeader("jwt_token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVU0VSX0lEIjozLCJOQU1FIjoi7J207IaM7Z2sIiwiaWF0IjoxNjYyNjIxNjE1LCJleHAiOjE2ODg1NDE2MTUsImlzcyI6IkVDT1JFIn0.L_0vFQhGPRUv_Ew74A1_3U9DUylt6EcM7eU3XPpCwcY")
            .build()
        // Response
        val response = it.proceed(request)
        response
    }.build()

    private val getRetrofit by lazy{
        Retrofit.Builder()
            .baseUrl(TEST_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getRetrofitService : ApiInterface by lazy{
        getRetrofit.create(ApiInterface::class.java)
    }
}