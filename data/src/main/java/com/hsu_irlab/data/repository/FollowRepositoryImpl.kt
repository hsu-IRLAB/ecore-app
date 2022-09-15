package com.hsu_irlab.data.repository

import com.hsu_irlab.data.remote.dataSource.FollowDataSource
import com.hsu_irlab.domain.model.DomainFollow
import com.hsu_irlab.domain.repository.FollowRepository

class FollowRepositoryImpl(
    private val api: FollowDataSource
):FollowRepository {
    override suspend fun getFollow(type: String): List<DomainFollow> {
        return api.getFollow(type).Data.map{ it.toDomainFollow() }
    }
}