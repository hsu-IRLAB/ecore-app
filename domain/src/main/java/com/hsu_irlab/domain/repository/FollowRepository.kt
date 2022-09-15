package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainFollow

interface FollowRepository {
    suspend fun getFollow(type: String): List<DomainFollow>
}