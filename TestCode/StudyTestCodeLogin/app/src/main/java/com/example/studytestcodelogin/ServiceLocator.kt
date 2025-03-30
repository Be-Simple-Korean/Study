package com.example.studytestcodelogin

import com.example.studytestcodelogin.data.FakeLoginApi
import com.example.studytestcodelogin.data.LoginApi
import com.example.studytestcodelogin.data.LoginRepository
import com.example.studytestcodelogin.domain.LoginRepositoryInterface

object ServiceLocator {
    private val loginApi by lazy { FakeLoginApi() }

    fun provideLoginRepository(): LoginRepositoryInterface {
        return LoginRepository(loginApi)
    }
}