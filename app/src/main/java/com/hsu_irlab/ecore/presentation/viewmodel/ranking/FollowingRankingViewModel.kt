package com.hsu_irlab.ecore.presentation.viewmodel.ranking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.domain.use_case.RankingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingRankingViewModel @Inject constructor(
    private val rankingUseCase: RankingUseCase
) : ViewModel() {
    private val _ranking = MutableLiveData<List<DomainRanking>>()
    val ranking: LiveData<List<DomainRanking>>
        get() = _ranking

    init {
        getFollowingRanking()
    }

    fun getFollowingRanking(){
        viewModelScope.launch {
            _ranking.value = rankingUseCase.getFollowingRanking()
        }
    }
}