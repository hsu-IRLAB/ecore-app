package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainFollow

data class FollowDto(
    val Message: String,
    val Data: List<Follow>
)

data class Follow(
    val profile_img: String,
    val name: String
) {
    fun toDomainFollow(): DomainFollow {
        return DomainFollow(
            profile_img = profile_img,
            name = name
        )
    }
}
