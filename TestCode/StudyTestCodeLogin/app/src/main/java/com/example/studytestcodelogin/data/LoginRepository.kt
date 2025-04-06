package com.example.studytestcodelogin.data

import com.example.studytestcodelogin.domain.LoginRepositoryInterface
import com.example.studytestcodelogin.domain.model.LoginRequest
import com.example.studytestcodelogin.domain.model.LoginResponse

open class LoginRepository(private val api: LoginApi) : LoginRepositoryInterface {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return api.login(request)
    }
}