package com.hsu_irlab.ecore.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hsu_irlab.data.Prefs
import com.hsu_irlab.domain.model.DomainBadge
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefs: Prefs
):ViewModel(){
    private var _isLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLogin : MutableLiveData<Boolean>
        get() = _isLogin

    init {
//        prefs.jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVU0VSX0lEIjo5LCJOQU1FIjoi6rmA7LCs7Z2sIiwiaWF0IjoxNjYzMDgwMTIwLCJleHAiOjE2ODkwMDAxMjAsImlzcyI6IkVDT1JFIn0.EDe4wa7TwsDB-y629HHI-SZf1RSLvw5Ip8fxmwGOyzA"
        if (prefs.jwt!="NO_TOKEN"){
            _isLogin.postValue(true)
        }
    }

    fun login(){
        _isLogin.postValue(true)
    }

}