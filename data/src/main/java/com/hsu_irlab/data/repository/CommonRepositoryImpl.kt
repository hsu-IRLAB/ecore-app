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
}