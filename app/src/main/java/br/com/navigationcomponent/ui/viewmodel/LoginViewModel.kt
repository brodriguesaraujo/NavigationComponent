package br.com.navigationcomponent.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val mUnauthorized = MutableLiveData<Boolean>()
    val mAuthorized = MutableLiveData<Boolean>()

    init {
        mUnauthorized.value = true
    }

    fun authetication(username: String, password: String) {
        val logged = username.isNotEmpty() && password.isNotEmpty()
        mAuthorized.value = logged
    }

}