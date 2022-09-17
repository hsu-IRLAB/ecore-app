package com.hsu_irlab.ecore.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.data.Prefs
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.domain.use_case.CommonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CampaignViewModel @Inject constructor(
    commonUseCase: CommonUseCase,
    prefs: Prefs
): ViewModel() {
    private var _campaignImg: MutableLiveData<List<DomainImages>> = MutableLiveData()
    val campaignImg : LiveData<List<DomainImages>>
        get() = _campaignImg

    init {
        viewModelScope.launch{
            val data = commonUseCase.getCampaignProfileImg(prefs.user_id)
            _campaignImg.postValue(data)
        }
    }

}