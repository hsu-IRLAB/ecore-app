package com.hsu_irlab.ecore.presentaion.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.data.RankingRepositoryImpl
import com.hsu_irlab.domain.model.DomainRanking
import kotlinx.coroutines.launch

class RankingViewModel(private val repository: RankingRepositoryImpl):ViewModel() {
    private val _retrofitRanking = MutableLiveData<List<DomainRanking>>()
    val retrofitRanking: MutableLiveData<List<DomainRanking>>
        get() = _retrofitRanking
    init { // 초기화 시 서버에서 데이터를 받아온다.
        viewModelScope.launch {
            _retrofitRanking.value = repository.getRanking()
        }
    }
    class Factory() : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RankingRepositoryImpl.getInstance()?.let { RankingViewModel(it) } as T
        }
    }

}