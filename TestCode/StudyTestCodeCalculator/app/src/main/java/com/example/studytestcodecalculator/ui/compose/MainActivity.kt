package com.example.studytestcodecalculator.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studytestcodecalculator.ui.CalculatorViewModel
import com.example.studytestcodecalculator.ui.Operation
import com.example.studytestcodecalculator.ui.compose.theme.StudyTestCodeCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyTestCodeCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var inputA by remember { mutableStateOf("") }
    var inputB by remember { mutableStateOf("") }

    val result = viewModel.result

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.testTag("inputA"),
            value = inputA,
            onValueChange = { inputA = it },
            label = { Text("Number A") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.testTag("inputB"),
            value = inputB,
            onValueChange = { inputB = it },
            label = { Text("Number B") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            OperationButton("+") { viewModel.calculate(inputA, inputB, Operation.ADD) }
            OperationButton("-") { viewModel.calculate(inputA, inputB, Operation.SUB) }
            OperationButton("×") { viewModel.calculate(inputA, inputB, Operation.MULTIPLE) }
            OperationButton("÷") { viewModel.calculate(inputA, inputB, Operation.DIVIDE) }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Result: ${result?.toString() ?: "Invalid Input"}",
            modifier = Modifier.testTag("resultText"),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun OperationButton(
    label: String,
    onClick: () -> Unit
) {
    Button(modifier = Modifier.testTag(label), onClick = onClick) {
        Text(label)
    }
}