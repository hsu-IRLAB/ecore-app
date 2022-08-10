package com.hsu_irlab.ecore.presentaion.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.data.RankingRepositoryImpl
import com.hsu_irlab.domain.model.DomainBaseRanking
import kotlinx.coroutines.launch

class RankingViewModel(private val repository: RankingRepositoryImpl):ViewModel() {
    private val _retrofitRanking = MutableLiveData<DomainBaseRanking>()
    val retrofitRanking: MutableLiveData<DomainBaseRanking>
        get() = _retrofitRanking
    init { // 초기화 시 서버에서 데이터를 받아온다.
        viewModelScope.launch {
            _retrofitRanking.value = repository.getRanking()
        }
    }
    class Factory(private val application : Application) : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RankingViewModel(RankingRepositoryImpl.getInstance(application)!!) as T
        }
    }

}