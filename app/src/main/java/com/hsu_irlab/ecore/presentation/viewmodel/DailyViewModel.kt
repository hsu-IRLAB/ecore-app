package com.hsu_irlab.ecore.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainDailyDetail
import com.hsu_irlab.domain.use_case.DailyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    dailyUseCase: DailyUseCase
): ViewModel() {
    private var _dailyDetail: MutableLiveData<DomainDailyDetail> = MutableLiveData()
    val dailyDetail: LiveData<DomainDailyDetail>
        get() = _dailyDetail

    init {
        viewModelScope.launch {
            val data = dailyUseCase.getDailyDetail()
            _dailyDetail.postValue(data)
        }
    }
}
