package br.com.navigationcomponent.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val mUnauthorized = MutableLiveData<Boolean>()
    val mAuthorized = MutableLiveData<Boolean>()
    var username = ""

    init {
        refuseAuthetication()
    }

    fun refuseAuthetication() {
        mUnauthorized.value = true
    }

    fun authetication(username: String, password: String) {
        val logged = username.isNotEmpty() && password.isNotEmpty()
        mAuthorized.value = logged
        this.username = username
    }

}