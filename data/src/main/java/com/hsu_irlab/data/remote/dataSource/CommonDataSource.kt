package com.hsu_irlab.data.remote.dataSource

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

//interface CommonDataSource {
//
//    @Multipart
//    @POST("/upload")
//    suspend fun addDaily(@Part file: MultipartBody.Part,@Body query:JsonObject): Response<GroupEdit>
//}