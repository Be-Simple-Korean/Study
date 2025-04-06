package com.example.studytestcodelogin

import com.example.studytestcodelogin.data.LoginRepository
import com.example.studytestcodelogin.domain.LoginRepositoryInterface
import com.example.studytestcodelogin.domain.model.LoginRequest
import com.example.studytestcodelogin.domain.model.LoginResponse
import com.example.studytestcodelogin.ui.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class LoginTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: LoginRepositoryInterface
    private val testDispatcher = StandardTestDispatcher()
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        repository = mock(LoginRepositoryInterface::class.java)
        viewModel = LoginViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun 로그인_성공_테스트() = runTest{
        // given
        val request = LoginRequest("test", "1234")
        val response = LoginResponse(true, "tester")
        whenever(repository.login(request)).thenReturn(response)

        // when
        viewModel.onIdChanged("test")
        viewModel.onPwChanged("1234")
        viewModel.onLoginClick()
        advanceUntilIdle()

        // then
        assertEquals(true, viewModel.state.isSuccess)
        verify(repository).login(request)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun 로그인_실패_테스트() = runTest{
        // given
        val request = LoginRequest("test", "12345")
        val response = LoginResponse(false, "null")
        whenever(repository.login(request)).thenReturn(response)

        // when
        viewModel.onIdChanged("test")
        viewModel.onPwChanged("12345")
        viewModel.onLoginClick()
        advanceUntilIdle()

        // then
        assertEquals(false, viewModel.state.isSuccess)
        verify(repository).login(request)
    }
}