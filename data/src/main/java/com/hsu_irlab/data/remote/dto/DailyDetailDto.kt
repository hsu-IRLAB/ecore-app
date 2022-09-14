package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.model.DomainDailyDetail

data class DailyDetailDto(
    val Data: List<DailyDetail>,
    val Message: String
)
data class DailyDetail(
    val daily_challenge_id: Int,
    val detail: String,
    val good_ex: String,
    val bad_ex: String,
){
    fun toDomainDailyDetail(): DomainDailyDetail {
        return DomainDailyDetail(
            daily_challenge_id=daily_challenge_id,
            detail=detail,
            good_ex=good_ex,
            bad_ex=bad_ex,
        )
    }
}

