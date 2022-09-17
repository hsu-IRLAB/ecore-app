package com.hsu_irlab.domain.repository

import com.hsu_irlab.domain.model.DomainImages

interface CommonRepository {
    suspend fun getProfileImg(type:String,count:String,target:Int):List<DomainImages>
    suspend fun addLike(type: String, img_id:Int)
    suspend fun delLike(type: String, img_id:Int)
    suspend fun addReport(type: String,img_id: Int)
}