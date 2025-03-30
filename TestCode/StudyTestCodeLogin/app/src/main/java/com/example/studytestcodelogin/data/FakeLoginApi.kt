package com.example.studytestcodelogin.data

import com.example.studytestcodelogin.domain.model.LoginRequest
import com.example.studytestcodelogin.domain.model.LoginResponse

class FakeLoginApi : LoginApi {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return if (request.id == "test" && request.password == "1234"){
            LoginResponse(true,"tester")
        }else {
            LoginResponse(false,null)
        }
    }
}