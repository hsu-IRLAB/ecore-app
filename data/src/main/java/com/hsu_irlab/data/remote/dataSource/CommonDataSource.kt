package com.hsu_irlab.data.remote.dataSource

import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dto.ImagePostResultDto
import com.hsu_irlab.data.remote.dto.ImagesDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface CommonDataSource {
    @GET("/image")
    suspend fun getImage(
        @Query("type")
        type: String,
        @Query("count")
        count: String,
        @Query("target")
        target: Int
    ):ImagesDto

    @Multipart
    @POST("/upload")
    suspend fun postImg(
        @Part type: MultipartBody.Part,
        @Part target: MultipartBody.Part,
        @Part img: MultipartBody.Part
    ): ImagePostResultDto
}