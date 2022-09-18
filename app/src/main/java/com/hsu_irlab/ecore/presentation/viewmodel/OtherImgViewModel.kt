package com.hsu_irlab.ecore.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.domain.use_case.CommonUseCase
import com.hsu_irlab.domain.use_case.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtherImgViewModel @Inject constructor(
    private val commonUseCase: CommonUseCase,
    private val userUseCase:UserUseCase
):ViewModel() {
    private var _image: MutableLiveData<DomainImages?> = MutableLiveData()
    val image : LiveData<DomainImages?>
        get() = _image

    private var _userInfo: MutableLiveData<DomainUserInfo> = MutableLiveData()
    val userInfo : MutableLiveData<DomainUserInfo>
        get() = _userInfo

    fun getOneImg(type:String,target:Int,img:Int)
    {
        viewModelScope.launch {
            _image.value=null
            _image.value=commonUseCase.getOneImg(type,target,img)
        }
    }

    fun getOneCampaignImg(target:Int,img:Int)
    {
        viewModelScope.launch {
            _image.value=null
            _image.value=commonUseCase.getOneCampaignImg(target,img)
        }
    }

    fun getUserInfo(user_id:Int){
        viewModelScope.launch {
            _userInfo.value=userUseCase.getUserInfo(user_id)
        }
    }

    fun addLike(type: String,img_id: Int)
    {
        viewModelScope.launch {
            commonUseCase.addLike(type,img_id)
        }
    }
    fun delLike(type: String,img_id: Int)
    {
        viewModelScope.launch {
            commonUseCase.delLike(type,img_id)
        }
    }
    fun addReport(type: String,img_id: Int)
    {
        viewModelScope.launch {
            commonUseCase.addReport(type,img_id)
        }
    }

}