package com.hsu_irlab.ecore.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.data.UserInfoRepositoryImpl
import com.hsu_irlab.domain.model.DomainUserInfo
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserInfoRepositoryImpl,val user_id:Int): ViewModel() {
    private val _retrofitUserInfo = MutableLiveData<DomainUserInfo>()
    val retrofitUserInfo: MutableLiveData<DomainUserInfo>
        get() = _retrofitUserInfo
    init { // 초기화 시 서버에서 데이터를 받아온다.
        viewModelScope.launch {
//            _retrofitUserInfo.value = repository.getUserInfo(user_id)
            Log.e("dd", "${_retrofitUserInfo.value}", )
        }
    }
    class Factory(val user_id:Int) : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserInfoRepositoryImpl.getInstance()?.let {HomeViewModel(it,user_id) } as T
        }
    }

}