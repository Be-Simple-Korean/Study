package com.example.studytestcodelogin.domain

import com.example.studytestcodelogin.domain.model.LoginRequest
import com.example.studytestcodelogin.domain.model.LoginResponse

interface LoginRepositoryInterface {
    suspend fun login(request:LoginRequest): LoginResponse
}