package com.hsu_irlab.ecore.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainChallenge
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.domain.use_case.ChallengeUseCase
import com.hsu_irlab.domain.use_case.RankingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CanViewModel @Inject constructor(
    private val challengeUseCase: ChallengeUseCase

) : ViewModel() {
    private var _challengeList: MutableLiveData<List<DomainChallenge>> = MutableLiveData(listOf())
    val challengeList: MutableLiveData<List<DomainChallenge>>
        get() = _challengeList

    init {
        can()
    }

    private fun can(){
        viewModelScope.launch {
            val data = challengeUseCase.getChallenge()
            val res = data.filter { it.user_challenge_id!=null}
            Log.e("TAG", "can: $res", )
            _challengeList.postValue(res)
        }

    }
}