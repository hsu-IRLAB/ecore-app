package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainBadge

interface BadgeRepository {
   suspend fun getBadge():List<DomainBadge>
}