package com.hsu_irlab.data.repository

import android.util.Log
import com.hsu_irlab.data.remote.BadgeDataSource
import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.repository.BadgeRepository

class BadgeRepositoryImpl (
    private val api: BadgeDataSource
    ) :BadgeRepository{
    override suspend fun getBadge(): List<DomainBadge> {
        val data = api.getBadge()
        return data.body()?.Data?.map { it.toDomainBadge() } ?: arrayListOf()
    }
}