package com.hsu_irlab.ecore.presentaion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.data.UserExistRepositoryImpl
import com.hsu_irlab.domain.model.DomainChangeName
import com.hsu_irlab.domain.model.DomainUserExist
import kotlinx.coroutines.launch

class ChangeNickNameDialogViewModel(
    private val userExistRepository: UserExistRepositoryImpl
):ViewModel() {
    private val _retrofitUserExist = MutableLiveData<DomainUserExist>()
    val retrofitUserExist: MutableLiveData<DomainUserExist>
        get() = _retrofitUserExist

    private val _retrofitChangeName = MutableLiveData<DomainChangeName>()
    val retrofitChangeName: MutableLiveData<DomainChangeName>
    get() = _retrofitChangeName

    fun checkUserExist(name : String)  {
        viewModelScope.launch {
            _retrofitUserExist.value = userExistRepository.getUserExist(name)
        }
    }

    fun changeName(name: String) {
        viewModelScope.launch{
            _retrofitChangeName.value = userExistRepository.putChangeName(name)
        }

    }

    class Factory() : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserExistRepositoryImpl.getInstance()?.let { ChangeNickNameDialogViewModel(it) } as T
        }
    }
}
