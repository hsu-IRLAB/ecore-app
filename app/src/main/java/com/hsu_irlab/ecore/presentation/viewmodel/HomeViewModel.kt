package com.hsu_irlab.ecore.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsu_irlab.domain.model.DomainDaily
import com.hsu_irlab.domain.model.DomainDailyInfo
import com.hsu_irlab.domain.model.DomainImages
import com.hsu_irlab.domain.use_case.CommonUseCase
import com.hsu_irlab.domain.use_case.DailyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dailyUseCase: DailyUseCase,
    private val commonUseCase: CommonUseCase,
): ViewModel() {
    private var _dailyInfo: MutableLiveData<DomainDaily> = MutableLiveData()
    val dailyInfo : MutableLiveData<DomainDaily>
        get() = _dailyInfo

    private var _img: MutableLiveData<List<DomainImages>> = MutableLiveData()
    val img : MutableLiveData<List<DomainImages>>
        get() = _img




    var dailyData :DomainDaily = DomainDaily("",-1, DomainDailyInfo("",-1,""))


    init {
        getDaily()
        getDailyDone()
    }
    fun getDaily(){
        viewModelScope.launch{
            val data = dailyUseCase.getDaily()
            dailyData =data
            _dailyInfo.postValue(data)
        }
    }
    private fun getDailyDone(){
        viewModelScope.launch{
            //TODO API이슈 타겟 9로 고정
            val data = commonUseCase.getProfileImg("daily",9)
            _img.postValue(data)
        }
    }

}