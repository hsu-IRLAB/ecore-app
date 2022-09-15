package com.hsu_irlab.ecore.presentation.viewmodel.userChallenge

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
class UserWholeChallengeViewModel @Inject constructor(
    private val prefs: Prefs,
    private val userDailyUseCase: CommonUseCase
): ViewModel() {
    private val _userChallenge = MutableLiveData<List<DomainImages>>()
    val userChallenge: LiveData<List<DomainImages>>
        get() = _userChallenge

    init {
        getWholeRanking()
    }

    fun getWholeRanking(){
        viewModelScope.launch {
            _userChallenge.value = userDailyUseCase.getChallengeProfileImg(prefs.user_id)
        }
    }
}