package com.hsu_irlab.ecore.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.domain.use_case.CommonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileImgViewModel @Inject constructor(
    private val useCase: CommonUseCase
):ViewModel(){
    private var _images: MutableLiveData<List<DomainImages>> = MutableLiveData()
    val images : MutableLiveData<List<DomainImages>>
        get() = _images

    fun getDailyImages(type:String,user_id:Int)
    {
        viewModelScope.launch {
            _images.value=useCase.getProfileImg(type,user_id)
        }
    }

}
