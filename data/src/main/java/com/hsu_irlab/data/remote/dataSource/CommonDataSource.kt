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


    @FormUrlEncoded
    @POST("/like")
    suspend fun addLike(
        @Field("type") type:String,
        @Field("img_id") img_id:Int
    )


    @DELETE("/like")
    suspend fun delLike(
        @Field("type") type:String,
        @Field("img_id") img_id:Int
    )

    @FormUrlEncoded
    @POST("/report")
    suspend fun addReport(
        @Field("type") type:String,
        @Field("img_id") img_id:Int
    )

    @Multipart
    @POST("/upload")
    fun postImg(
        @Body query: JsonObject,
        @Part file: MultipartBody.Part
    ): ImagePostResultDto

}