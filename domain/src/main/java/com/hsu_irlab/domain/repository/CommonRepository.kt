package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainImagePostResult
import com.hsu_irlab.domain.model.DomainImages
import java.io.File

interface CommonRepository {
    suspend fun getProfileImg(type:String,count:String,target:Int):List<DomainImages>

    suspend fun postImage(type:String,target: Int,image: File):DomainImagePostResult
}