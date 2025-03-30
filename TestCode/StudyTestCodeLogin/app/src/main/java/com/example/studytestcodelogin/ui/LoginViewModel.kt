package com.example.studytestcodelogin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studytestcodelogin.domain.LoginRepositoryInterface
import com.example.studytestcodelogin.domain.model.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel(private val repo : LoginRepositoryInterface) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onIdChanged(newId: String) {
        state = state.copy(id = newId)
    }

    fun onPwChanged(newPw: String) {
        state = state.copy(pw = newPw)
    }

    fun onLoginClick() {
        viewModelScope.launch {
            val res = repo.login(LoginRequest(state.id, state.pw))
            state = state.copy(isSuccess = res.success)
        }
    }
}