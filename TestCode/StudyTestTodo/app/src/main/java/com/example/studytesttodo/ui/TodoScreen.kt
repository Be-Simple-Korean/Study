package com.example.studytesttodo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studytesttodo.domain.Todo

@Composable
fun TodoScreen(
    viewModel: TodoViewModel,
    navController: NavController
) {
    val todos by viewModel.todos.collectAsState()
    var todoToDelete by remember { mutableStateOf<Todo?>(null) }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate("add")
        }) {
            Icon(Icons.Default.Add, contentDescription = "추가")
        }
    }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(todos) { todo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { viewModel.toggleTodo(todo) },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                   Text(todo.title)
                    IconButton(onClick = {
                        todoToDelete = todo
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "삭제")
                    }
                }
            }
        }
        if (todoToDelete!=null){
            ConfirmDialog(
                message = "할 일을 삭제할까요?",
                onConfirm = {
                    viewModel.deleteTodo(todoToDelete!!)
                    todoToDelete = null
                },
                onDismiss = { todoToDelete = null }
            )
        }
    }
}