package com.hsu_irlab.data.remote.dataSourceImpl

import com.google.gson.JsonObject
import com.hsu_irlab.data.remote.dataSource.CommonDataSource
import com.hsu_irlab.data.remote.dto.ImagePostResultDto
import com.hsu_irlab.data.remote.dto.ImagesDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CommonDataSourceImpl @Inject constructor(private val retrofit: Retrofit): CommonDataSource {
    override suspend fun getImage(type: String, count: String, target: Int): ImagesDto {
        return retrofit.create(CommonDataSource::class.java).getImage(type, count, target)
    }

    override fun postImg(query: JsonObject, file: MultipartBody.Part): Call<ImagePostResultDto> {
        return retrofit.create(CommonDataSource::class.java).postImg(query,file)
    }

}