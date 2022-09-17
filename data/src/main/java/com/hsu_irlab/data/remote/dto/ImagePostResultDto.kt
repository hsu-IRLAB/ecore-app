package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainImagePostResult

data class ImagePostResultDto (
    var Message: String? = null
){
    fun toDomainImagePostResult(): DomainImagePostResult{
        return DomainImagePostResult(
            Message = Message
        )
    }
}
