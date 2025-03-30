package com.example.studytestcodelogin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
){
    val state = viewModel.state

    Column(modifier = modifier) {
        TextField(value=state.id, onValueChange = viewModel::onIdChanged)
        TextField(value=state.pw, onValueChange = viewModel::onPwChanged)
        Button(onClick = viewModel::onLoginClick) {
            Text("Login")
        }

        if (state.isSuccess != null) {
            Text(if (state.isSuccess == true) "Success" else "Failed")
        }
    }
}