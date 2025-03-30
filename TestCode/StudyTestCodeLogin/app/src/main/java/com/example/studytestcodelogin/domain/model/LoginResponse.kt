package com.example.studytestcodelogin.domain.model

data class LoginResponse (
    val success:Boolean,
    val token:String?
)