package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainFollow
import com.hsu_irlab.domain.model.DomainFollowSearch

interface FollowRepository {
    suspend fun getFollow(type: String): List<DomainFollow>
    suspend fun getFollowSearch(name: String): List<DomainFollowSearch>?
    suspend fun addFollow(target_id : Int)
}