package com.hsu_irlab.ecore.presentaion.viewmodel.ranking

import android.util.Log
import androidx.lifecycle.*
import com.hsu_irlab.data.RankingRepositoryImpl
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.ecore.EcoreApp
import kotlinx.coroutines.launch

class RankingViewModel(private val repository: RankingRepositoryImpl) : ViewModel() {
    private val _myRanking= MutableLiveData<DomainRanking>()
    val myRanking: MutableLiveData<DomainRanking> get() = _myRanking
    fun getMyRanking(type:String){
        viewModelScope.launch {
            _myRanking.value=repository.getRanking(type).filter { it.user_id== EcoreApp.prefs.user_id }.get(0)
            Log.e("launch", "getMyRanking: ${_myRanking.value?.row_num}", )
        }
    }

    class Factory() : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RankingRepositoryImpl.getInstance()?.let { RankingViewModel(it) } as T
        }
    }
}