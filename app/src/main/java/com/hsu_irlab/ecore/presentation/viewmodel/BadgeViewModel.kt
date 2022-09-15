package com.hsu_irlab.ecore.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.use_case.BadgeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BadgeViewModel @Inject constructor(
    badgeUseCase: BadgeUseCase
): ViewModel() {
    private var _itemList: MutableLiveData<List<DomainBadge>> = MutableLiveData(listOf())
    val itemList : MutableLiveData<List<DomainBadge>>
        get() = _itemList

    init {
        viewModelScope.launch{
            val data = badgeUseCase.getBadge()
            _itemList.postValue(data)
        }
        Log.e(javaClass.simpleName, "나 곧 눈물 흘림: ${itemList.value}", )
    }

}