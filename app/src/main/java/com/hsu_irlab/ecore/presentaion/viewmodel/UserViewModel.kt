package com.hsu_irlab.ecore.presentaion.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.data.UserInfoRepositoryImpl
import com.hsu_irlab.domain.model.DomainUserInfo
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserInfoRepositoryImpl): ViewModel() {
    private val userId = 99   // 임시 userid 실제로는 사용자의 아이디가 들어가야함

    private val _retrofitUserInfo = MutableLiveData<DomainUserInfo>()
    val retrofitUserInfo: MutableLiveData<DomainUserInfo>
        get() = _retrofitUserInfo

    init {
        viewModelScope.launch {
            _retrofitUserInfo.value = repository.getUserInfo(userId)
        }
    }
    class Factory() : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserInfoRepositoryImpl.getInstance()?.let { UserViewModel(it) } as T
        }
    }
}