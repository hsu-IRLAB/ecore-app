package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainFollow
import com.hsu_irlab.domain.repository.FollowRepository

class FollowUseCase(
    private val repository: FollowRepository
) {
    suspend fun getFollowing():List<DomainFollow>
    {
        return repository.getFollow("following")
    }
    suspend fun getFollower():List<DomainFollow>
    {
        return repository.getFollow("follower")
    }
}