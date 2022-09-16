package com.hsu_irlab.ecore.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainReview
import com.hsu_irlab.domain.use_case.ChallengeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewDialogViewModel @Inject constructor(
    private val reviewUseCase: ChallengeUseCase
): ViewModel(){

    private val _review = MutableLiveData<DomainReview>()
    val review: MutableLiveData<DomainReview>
    get() = _review

    fun postReview(uCId:Int, content:String){
        viewModelScope.launch {
            val data = reviewUseCase.postReview(uCId,content)
            _review.postValue(data)
        }
    }
}