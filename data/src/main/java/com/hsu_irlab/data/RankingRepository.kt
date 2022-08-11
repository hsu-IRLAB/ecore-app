package com.hsu_irlab.data

import com.hsu_irlab.data.response.BaseResponse

interface RankingRepository{
    suspend fun getRanking(): BaseResponse

}