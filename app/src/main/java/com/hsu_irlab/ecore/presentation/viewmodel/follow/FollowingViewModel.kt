package com.hsu_irlab.ecore.presentation.viewmodel.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainFollow
import com.hsu_irlab.domain.use_case.FollowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FollowingViewModel@Inject constructor(
    private val followUseCase: FollowUseCase
) : ViewModel() {
    private val _following= MutableLiveData<List<DomainFollow>>()
    val following: LiveData<List<DomainFollow>> get() = _following

    init {
        getFollowing()
    }

    fun getFollowing()
    {
        viewModelScope.launch { _following.value=followUseCase.getFollowing() }
    }
}