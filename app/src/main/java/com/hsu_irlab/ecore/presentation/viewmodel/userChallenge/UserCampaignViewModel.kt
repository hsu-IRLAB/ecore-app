package com.hsu_irlab.ecore.presentation.viewmodel.userChallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.data.Prefs
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.domain.model.DomainRanking
import com.hsu_irlab.domain.use_case.CommonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserCampaignViewModel @Inject constructor(
    private val prefs: Prefs,
    private val userChallengeUseCase:CommonUseCase
): ViewModel() {
    private val _userCampaign = MutableLiveData<List<DomainImages>>()
    val userCampaign: LiveData<List<DomainImages>>
        get() = _userCampaign

    init {
        getWholeRanking()
    }

    fun getWholeRanking(){
        viewModelScope.launch {
            _userCampaign.value = userChallengeUseCase.getCampaignProfileImg(prefs.user_id)
        }
    }
}