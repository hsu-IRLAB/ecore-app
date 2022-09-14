package com.hsu_irlab.ecore.presentation.viewmodel.ranking

import androidx.lifecycle.*
import com.hsu_irlab.data.Prefs
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.domain.use_case.RankingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
   private val rankingUseCase: RankingUseCase,
    private val prefs: Prefs
    ) : ViewModel() {
    private val _myRanking= MutableLiveData<DomainRanking>()
    val myRanking: MutableLiveData<DomainRanking> get() = _myRanking

    private val _ranking = MutableLiveData<List<DomainRanking>>()
    val ranking: MutableLiveData<List<DomainRanking>>
        get() = _ranking
    init { // 초기화 시 서버에서 데이터를 받아온다.
        viewModelScope.launch {
            _ranking.value = rankingUseCase.getRanking("all")
        }
    }
    fun getRanking(type: String){
        viewModelScope.launch {
            _ranking.value = rankingUseCase.getRanking(type)
        }

    }

    fun getMyRanking(type:String){
        viewModelScope.launch {
            _myRanking.value=rankingUseCase.getRanking(type).filter { it.user_id== prefs.user_id }.get(0)
        }
    }
}