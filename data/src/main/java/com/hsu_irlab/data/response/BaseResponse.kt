package com.hsu_irlab.data.response

import com.google.gson.annotations.SerializedName
import com.hsu_irlab.domain.model.DomainBaseRanking
import com.hsu_irlab.domain.model.DomainRanking

data class BaseResponse (
//    val Message : String,
    @SerializedName("Data") val data : List<RankingResponse>
    )

fun BaseResponse.toDomainBaseRanking(): DomainBaseRanking = DomainBaseRanking(
     data as List<DomainRanking>)

