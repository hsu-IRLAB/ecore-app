package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.domain.repository.CommonRepository

class CommonUseCase(
    private val repository: CommonRepository
) {
    suspend fun getDailyProfileImg(target:Int):List<DomainImages>
    {
        return repository.getProfileImg("daily","all",target)
    }
    suspend fun getChallengeProfileImg(target:Int):List<DomainImages>
    {
        return repository.getProfileImg("challenge","all",target)
    }
    suspend fun getCampaignProfileImg(target:Int):List<DomainImages>{
        return repository.getProfileImg("campaign","all",target)
    }
    suspend fun getProfileImg(type:String,target:Int):List<DomainImages>
    {
        return repository.getProfileImg(type,"all",target)
    }
    suspend fun getOneImg(type: String,target:Int,img:Int):DomainImages
    {
        return repository.getProfileImg(type, "all", target).first{ it.img_id == img }
    }
    suspend fun getOneCampaignImg(target:Int,img:Int):DomainImages
    {
        return repository.getProfileImg("campaign", "all", target).first{ it.campaign_image_id == img }
    }
    suspend fun addLike(type: String,img_id:Int)
    {
        repository.addLike(type, img_id)
    }
    suspend fun delLike(type: String,img_id:Int)
    {
        repository.delLike(type, img_id)
    }
    suspend fun addReport(type: String,img_id: Int)
    {
        repository.addReport(type, img_id)
    }



}