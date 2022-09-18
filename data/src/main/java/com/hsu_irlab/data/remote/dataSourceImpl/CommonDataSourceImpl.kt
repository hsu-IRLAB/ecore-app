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

class CommonDataSourceImpl @Inject constructor(private val retrofit: Retrofit) : CommonDataSource {
    override suspend fun getImage(type: String, count: String, target: Int): ImagesDto {
        return retrofit.create(CommonDataSource::class.java).getImage(type, count, target)
    }

    override suspend fun postImg(
        type: MultipartBody.Part,
        target: MultipartBody.Part,
        img: MultipartBody.Part
    ): ImagePostResultDto {
        return retrofit.create(CommonDataSource::class.java).postImg(type, target, img)
    }
    override suspend fun addLike(type: String, img_id: Int) {
        retrofit.create(CommonDataSource::class.java).addLike(type, img_id)
    }

    override suspend fun delLike(type: String, img_id: Int) {
        retrofit.create(CommonDataSource::class.java).delLike(type, img_id)
    }

    override suspend fun addReport(type: String, img_id: Int) {
        retrofit.create(CommonDataSource::class.java).addReport(type, img_id)
    }

//    override fun postImg(
//        query: JsonObject,
//        file: MultipartBody.Part
//    ): ImagePostResultDto {
//        return retrofit.create(CommonDataSource::class.java).postImg(query, file)
//
//    }

}
