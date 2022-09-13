package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainBadge

data class BadgeDto(
    val Data: List<BadgeData>,
    val Message: String
)

data class BadgeData(
    val badge_date: String,
    val badge_img: String,
    val detail: String,
    val title: String
){
    fun toDomainBadge():DomainBadge{
        return DomainBadge(
            badge_date=badge_date,
            badge_img=badge_img,
            detail = detail,
            title = title
        )
    }
}