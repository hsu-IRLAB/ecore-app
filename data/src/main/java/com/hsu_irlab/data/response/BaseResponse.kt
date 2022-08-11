package com.hsu_irlab.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse (
    @SerializedName("Data") val data : List<RankingResponse>
    )

