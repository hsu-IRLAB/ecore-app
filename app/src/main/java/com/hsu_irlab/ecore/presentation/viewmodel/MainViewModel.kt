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
import com.hsu_irlab.domain.use_case.CommonUseCase
import com.hsu_irlab.domain.use_case.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    prefs: Prefs,
    private val userUseCase: UserUseCase,
    private val commonUseCase: CommonUseCase
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
        prefs.jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVU0VSX0lEIjo5LCJOQU1FIjoi6rmA7LCs7Z2sIiwiaWF0IjoxNjYzMDgwMTIwLCJleHAiOjE2ODkwMDAxMjAsImlzcyI6IkVDT1JFIn0.EDe4wa7TwsDB-y629HHI-SZf1RSLvw5Ip8fxmwGOyzA"
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

/*
    fun postImg(type: String, target: Int){
        img.value?.let { BitmapConvertFile(it,"/test") }
        viewModelScope.launch {
            val data=commonUseCase.postImg(type,target,)
        }
    }
*/



    private fun BitmapConvertFile(bitmap: Bitmap,filePath: String):File{
        val directory = File("/image")
        if (!directory.exists()) {       // 원하는 경로에 폴더가 있는지 확인
            directory.mkdirs();    // 하위폴더를 포함한 폴더를 전부 생성
        }

        val file: File = File(filePath)

        var out : OutputStream? = null

        try {
            file.createNewFile()
            out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out)
        }catch (e:Exception){
            e.printStackTrace()
        }
        finally {
            try {
                out?.close()
            }
            catch (e: IOException){
                e.printStackTrace()
            }
        }
        return file
    }
    
}