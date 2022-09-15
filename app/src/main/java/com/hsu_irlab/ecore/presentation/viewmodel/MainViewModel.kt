package com.hsu_irlab.ecore.presentation.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.hsu_irlab.data.Prefs
import com.hsu_irlab.domain.model.DomainUserInfo
import com.hsu_irlab.domain.use_case.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    prefs: Prefs,
    private val userUseCase: UserUseCase
):ViewModel(){
    private var _isLogin: MutableLiveData<Boolean> = MutableLiveData(false)

    private var _userInfo: MutableLiveData<DomainUserInfo> = MutableLiveData()
    val userInfo : MutableLiveData<DomainUserInfo>
        get() = _userInfo

    private var _img: MutableLiveData<Bitmap> = MutableLiveData()
    val img : LiveData<Bitmap>
        get() = _img

    val isLogin : MutableLiveData<Boolean>
        get() = _isLogin

    init {
        //TODO jwt바꾸는 부분
        prefs.jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVU0VSX0lEIjozLCJOQU1FIjoi7JWI7IiY66-8IiwiaWF0IjoxNjYzMDU2NjQ5LCJleHAiOjE2ODg5NzY2NDksImlzcyI6IkVDT1JFIn0.ZGccbO--lxu-0OVr0f1e8q5IalUllbTPLOEBAAOtOjI"
        val jwt = JWT(prefs.jwt)
        val user_id = jwt.getClaim("USER_ID").asInt()
        if (user_id != null) {
            prefs.user_id = user_id
        }
        Log.e("TAG", "해당 유저 아이디 $user_id", )

        getUserInfo(prefs.user_id)
        if (prefs.jwt!="NO_TOKEN"){
            _isLogin.postValue(true)
        }
    }

    fun picture(bitmap: Bitmap){
        Log.e("dd", "picture: mainViemodel", )
        _img.postValue(bitmap)
    }

    fun pictureClear(){
        _img = MutableLiveData()
    }
    fun login(){
        _isLogin.postValue(true)
    }
    fun getUserInfo(user_id:Int){
        viewModelScope.launch {
            _userInfo.postValue(userUseCase.getUserInfo(user_id))
        }
    }


}