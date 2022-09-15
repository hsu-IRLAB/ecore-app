package com.hsu_irlab.ecore.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainCampaign
import com.hsu_irlab.domain.model.DomainChallenge
import com.hsu_irlab.domain.use_case.CampaignUseCase
import com.hsu_irlab.domain.use_case.ChallengeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    campaignUseCase: CampaignUseCase,
//    challengeUseCase: ChallengeUseCase
): ViewModel() {
    private var _campaignList: MutableLiveData<List<DomainCampaign>> = MutableLiveData(listOf())
    val campaignList: MutableLiveData<List<DomainCampaign>>
        get() = _campaignList

    private var _challengeList: MutableLiveData<List<DomainChallenge>> = MutableLiveData(listOf())
    val challengeList: MutableLiveData<List<DomainChallenge>>
        get() = _challengeList

    init {
        viewModelScope.launch {
            _campaignList.postValue(campaignUseCase.getCampaign())
//            _challengeList.postValue(challengeUseCase.getChallenge())
        }
    }
}

