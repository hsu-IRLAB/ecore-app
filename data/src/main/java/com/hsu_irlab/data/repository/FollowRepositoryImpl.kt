package com.hsu_irlab.data.repository

import com.hsu_irlab.data.remote.dataSource.FollowDataSource
import com.hsu_irlab.domain.model.DomainFollow
import com.hsu_irlab.domain.model.DomainFollowSearch
import com.hsu_irlab.domain.repository.FollowRepository

class FollowRepositoryImpl(
    private val api: FollowDataSource
):FollowRepository {
    override suspend fun getFollow(type: String): List<DomainFollow> {
        return api.getFollow(type).Data.map{ it.toDomainFollow() }
    }

    override suspend fun getFollowSearch(name: String): List<DomainFollowSearch>? {
        return api.getFollowSearch(name).Data?.map { it.toDomainFollowSearch() }
    }

    override suspend fun addFollow(target_id: Int) {
        api.addFollow(target_id)
    }
}