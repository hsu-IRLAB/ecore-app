package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.repository.BadgeRepository

class BadgeUseCase(
    private val repository: BadgeRepository
){
    suspend fun getBadge():List<DomainBadge>{
        return repository.getBadge()
    }

}