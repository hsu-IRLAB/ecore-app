package com.hsu_irlab.ecore.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.model.DomainUserExist
import com.hsu_irlab.domain.use_case.UserUseCase
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.data.Prefs
import com.hsu_irlab.domain.model.DomainUserChangeName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeNickNameDialogViewModel @Inject constructor(
    private val prefs: Prefs,
    private val userUseCase: UserUseCase
) : ViewModel() {
    val id = prefs.user_id

    private val _userExist = MutableLiveData<DomainUserExist>()
    val userExist: MutableLiveData<DomainUserExist>
        get() = _userExist

    private val _changeName = MutableLiveData<DomainUserChangeName>()
    val changeName: MutableLiveData<DomainUserChangeName>
        get() = _changeName



    fun checkUserExist(name : String)  {
        viewModelScope.launch {
            val data = userUseCase.getUserExist(name)
            _userExist.postValue(data)
        }

    }

    fun changeName(name: String) {
        viewModelScope.launch{
            val data = userUseCase.putUserName(name)
            prefs.jwt = data.Data
            _changeName.postValue(data)
        }
        Log.e(javaClass.simpleName, "changeName: ${changeName.value?.Message}", )
    }

}