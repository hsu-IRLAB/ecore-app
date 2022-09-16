package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainReview

data class ReviewDto (
    val Message : String,
    val Error_Message: String
    ){
    fun toDomainReview() : DomainReview{
        return DomainReview(
            Message=Message,
            Error_Message = Error_Message
        )
    }
}