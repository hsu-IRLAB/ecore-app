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
class FollowerViewModel@Inject constructor(
    private val followUseCase: FollowUseCase
) : ViewModel() {
    private val _follower= MutableLiveData<List<DomainFollow>>()
    val follower: LiveData<List<DomainFollow>> get() = _follower

    init {
        getFollower()
    }

    fun getFollower()
    {
        viewModelScope.launch { _follower.value=followUseCase.getFollower() }
    }
}