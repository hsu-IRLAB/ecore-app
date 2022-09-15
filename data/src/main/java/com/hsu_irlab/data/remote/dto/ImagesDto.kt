package com.hsu_irlab.data.remote.dto

import com.hsu_irlab.domain.model.DomainImages

data class ImagesDto(
    val Message:String,
    val Data: List<Images>
)

data class Images(
    val img_id:Int?,
    val title:String?,
    val img:String?,
    val date:String?,
    val good:Int?,
    val campaign_image_id:Int?,
    val campaign_img:String?,
    val is_review:Int?
){
    fun toDomainImages() : DomainImages{
        return DomainImages(
            img_id=img_id,
            title=title,
            img=img,
            date=date,
            good=good,
            campaign_image_id=campaign_image_id,
            campaign_img=campaign_img,
            is_review=is_review
        )
    }
}
