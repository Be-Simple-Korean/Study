package com.example.studytestcodelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studytestcodelogin.data.LoginApi
import com.example.studytestcodelogin.data.LoginRepository
import com.example.studytestcodelogin.domain.LoginRepositoryInterface
import com.example.studytestcodelogin.ui.LoginScreen
import com.example.studytestcodelogin.ui.LoginViewModel
import com.example.studytestcodelogin.ui.theme.StudyTestCodeLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel = remember { LoginViewModel(ServiceLocator.provideLoginRepository()) }
            StudyTestCodeLoginTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  LoginScreen(viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StudyTestCodeLoginTheme {
        Greeting("Android")
    }
}