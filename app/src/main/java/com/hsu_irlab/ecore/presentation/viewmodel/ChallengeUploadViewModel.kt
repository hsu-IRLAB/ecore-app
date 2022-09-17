package com.hsu_irlab.ecore.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainBadge
import com.hsu_irlab.domain.model.DomainChallengeDetail
import com.hsu_irlab.domain.model.DomainChallengeUpload
import com.hsu_irlab.domain.model.DomainChallengeUploadDetail
import com.hsu_irlab.domain.use_case.ChallengeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeUploadViewModel @Inject constructor(
    private val challengeUseCase: ChallengeUseCase
) : ViewModel() {

    private var _challengeDetail: MutableLiveData<DomainChallengeUpload> = MutableLiveData()
    val challengeDetail : MutableLiveData<DomainChallengeUpload>
        get() = _challengeDetail

    init {

    }

    fun getChallengeUploadDetail(id:Int){
        viewModelScope.launch {
            val data = challengeUseCase.getChallengeUploadDetail(id)
            Log.e("TAG", "getChallengeUploadDetail: $data", )
            _challengeDetail.postValue(data[0])
        }
    }
}