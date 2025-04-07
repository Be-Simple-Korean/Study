package com.example.studytesttodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    onSave: (String)->Unit,
    onBack: ()->Unit
){
    var text by remember { mutableStateOf("")}
    var showConfirm by remember { mutableStateOf(false)}

    Scaffold (
        topBar = { TopAppBar(title = {Text("할 일 추가")}) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (text.isNotBlank()) showConfirm = true
            }) {
                Icon(Icons.Default.Check, contentDescription = "Save")
            }
        }
    ){ padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)){
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text("할 일을 입력하세요") }
            )
        }
    }

    if(showConfirm){
        ConfirmDialog(
            message = "할 일을 추가할까요?",
            onConfirm = {
                onSave(text)
                onBack()
            },
            onDismiss = {showConfirm=false}
        )
    }
}