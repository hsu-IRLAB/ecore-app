package com.hsu_irlab.ecore.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.domain.use_case.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userUseCase: UserUseCase
):ViewModel() {
    private var _userInfo: MutableLiveData<DomainUserInfo> = MutableLiveData()
    val userInfo : MutableLiveData<DomainUserInfo>
        get() = _userInfo

    fun getUserInfo(user_id:Int){
        viewModelScope.launch {
            _userInfo.value=userUseCase.getUserInfo(user_id)
        }
    }
}