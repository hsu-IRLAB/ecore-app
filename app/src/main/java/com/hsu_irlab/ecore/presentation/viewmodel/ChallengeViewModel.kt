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
    private val campaignUseCase: CampaignUseCase,
): ViewModel() {
    private var _campaignList: MutableLiveData<List<DomainCampaign>> = MutableLiveData(listOf())
    var user_challenge_id=-1
    val campaignList: MutableLiveData<List<DomainCampaign>>
        get() = _campaignList
    init {
        viewModelScope.launch {
            _campaignList.postValue(campaignUseCase.getCampaign())
//            _challengeList.postValue(challengeUseCase.getChallenge())
        }
    }


}

