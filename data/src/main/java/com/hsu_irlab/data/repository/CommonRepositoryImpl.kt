package com.hsu_irlab.data.repository

import com.hsu_irlab.data.remote.dataSource.CommonDataSource
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.domain.repository.CommonRepository

class CommonRepositoryImpl(
    private val api: CommonDataSource
) : CommonRepository {
    override suspend fun getProfileImg(
        type: String,
        count: String,
        target: Int
    ): List<DomainImages> {
        return api.getImage(type, count, target).Data.map { it.toDomainImages() }
    }

    override suspend fun addLike(type: String, img_id: Int) {
        api.addLike(type,img_id)
    }

    override suspend fun delLike(type: String, img_id: Int) {
        api.delLike(type,img_id)
    }

    override suspend fun addReport(type: String, img_id: Int) {
        api.addReport(type,img_id)
    }
}