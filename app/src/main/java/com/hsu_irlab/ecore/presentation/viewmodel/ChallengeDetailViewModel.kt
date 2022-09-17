package com.hsu_irlab.ecore.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.model.DomainChallengeDetail
import com.hsu_irlab.domain.use_case.ChallengeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeDetailViewModel @Inject constructor(
    private val challengeUseCase: ChallengeUseCase
) : ViewModel() {

    private var _challengeDetail: MutableLiveData<DomainChallengeDetail> = MutableLiveData()
    val challengeDetail : MutableLiveData<DomainChallengeDetail>
        get() = _challengeDetail

    init {

    }

    fun getChallengeDetail(id:Int){
        viewModelScope.launch {
            val data = challengeUseCase.getChallengeDetail(id)
            _challengeDetail.postValue(data[0])
        }
    }
}