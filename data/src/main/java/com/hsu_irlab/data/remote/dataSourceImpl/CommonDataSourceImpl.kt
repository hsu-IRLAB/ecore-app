package com.hsu_irlab.data.remote.dataSourceImpl

import com.hsu_irlab.data.remote.dataSource.CommonDataSource
import com.hsu_irlab.data.remote.dto.ImagesDto
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CommonDataSourceImpl @Inject constructor(private val retrofit: Retrofit): CommonDataSource {
    override suspend fun getImage(type: String, count: String, target: Int): ImagesDto {
        return retrofit.create(CommonDataSource::class.java).getImage(type, count, target)
    }

    override suspend fun addLike(type: String, img_id: Int) {
        retrofit.create(CommonDataSource::class.java).addLike(type,img_id)
    }

    override suspend fun delLike(type: String, img_id: Int) {
        retrofit.create(CommonDataSource::class.java).delLike(type,img_id)
    }

    override suspend fun addReport(type: String, img_id: Int) {
        retrofit.create(CommonDataSource::class.java).addReport(type, img_id)
    }

}