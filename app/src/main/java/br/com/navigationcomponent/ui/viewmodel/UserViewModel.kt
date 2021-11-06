package br.com.navigationcomponent.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    sealed class AuthenticationState {
        object Unauthenticated : AuthenticationState()
        object Authenticated : AuthenticationState()
        object InvalidateAuthenticate : AuthenticationState()
    }

    private val mAuthorized = MutableLiveData<AuthenticationState>()
    val authorized: LiveData<AuthenticationState>
        get() = mAuthorized

    var username = ""

    init {
        refuseAuthetication()
    }

    fun refuseAuthetication() {
        mAuthorized.value = AuthenticationState.Unauthenticated
    }

    fun authetication(username: String, password: String) {
        val logged = username.isNotEmpty() && password.isNotEmpty()
        if (logged) {
            mAuthorized.value = AuthenticationState.Authenticated
        } else {
            mAuthorized.value = AuthenticationState.InvalidateAuthenticate
        }
        this.username = username
    }
}