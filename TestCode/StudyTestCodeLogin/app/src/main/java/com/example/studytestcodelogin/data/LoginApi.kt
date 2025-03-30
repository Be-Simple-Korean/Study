package com.example.studytestcodelogin.data

import com.example.studytestcodelogin.domain.model.LoginRequest
import com.example.studytestcodelogin.domain.model.LoginResponse

interface LoginApi {
    suspend fun login(request: LoginRequest):LoginResponse
}