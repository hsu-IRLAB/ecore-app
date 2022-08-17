package com.hsu_irlab.data.response.ranking

import com.google.gson.annotations.SerializedName


data class RakingResponse (
    @SerializedName("Data") val data : List<RankingDataResponse>
    )

