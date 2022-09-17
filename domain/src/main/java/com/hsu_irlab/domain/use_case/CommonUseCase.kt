package com.hsu_irlab.domain.use_case

import com.hsu_irlab.domain.model.DomainImagePostResult
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.domain.repository.CommonRepository
import java.io.File

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

    suspend fun postImg(type:String,target:Int,img: File):DomainImagePostResult{
        return repository.postImage(type,target,img)
    }
}