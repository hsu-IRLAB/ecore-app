package com.hsu_irlab.ecore.presentation.viewmodel.ranking

import android.util.Log
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
    val myRanking: LiveData<DomainRanking> get() = _myRanking

//    init { // 초기화 시 서버에서 데이터를 받아온다.
//       getMyRanking("all")
//    }

    fun getMyRanking(type:String){
        viewModelScope.launch {
            _myRanking.value=
                rankingUseCase.getMyRanking(type).first { it.user_id == prefs.user_id }
        }
    }
}