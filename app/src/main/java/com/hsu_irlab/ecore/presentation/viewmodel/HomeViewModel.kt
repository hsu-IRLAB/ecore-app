package com.hsu_irlab.ecore.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.use_case.DailyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    dailyUseCase: DailyUseCase
): ViewModel() {
    private var _dailyInfo: MutableLiveData<DomainDaily> = MutableLiveData()
    val dailyInfo : MutableLiveData<DomainDaily>
        get() = _dailyInfo

    init {
        viewModelScope.launch{
            val data = dailyUseCase.getDaily()
            _dailyInfo.postValue(data)
        }
    }

}