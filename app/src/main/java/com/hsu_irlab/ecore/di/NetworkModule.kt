package com.hsu_irlab.ecore.di

import com.hsu_irlab.data.Prefs
import com.hsu_irlab.ecore.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        prefs: Prefs
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://210.119.104.148:3000")
            .client(provideOkHttpClient(prefs))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        prefs: Prefs
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(provideOkHttpLogging())
            .addInterceptor{
                val request = it.request()
                    .newBuilder()
                    .addHeader("jwt_token", prefs.jwt?:"")
                    .build()
                // Response
                val response = it.proceed(request)
                response
            }

            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

}