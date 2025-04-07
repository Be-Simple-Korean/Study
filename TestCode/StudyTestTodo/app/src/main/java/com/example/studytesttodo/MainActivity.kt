package com.example.studytesttodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.studytesttodo.data.TodoRepositoryImpl
import com.example.studytesttodo.data.db.TodoDatabase
import com.example.studytesttodo.domain.TodoRepository
import com.example.studytesttodo.ui.AddTodoScreen
import com.example.studytesttodo.ui.TodoScreen
import com.example.studytesttodo.ui.TodoViewModel
import com.example.studytesttodo.ui.theme.StudyTestTodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "todo-db"
        ).build()

        val repository = TodoRepositoryImpl(db.todoDao())
        val viewModel = TodoViewModel(repository)

        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "todo") {
                composable("todo") {
                    TodoScreen(viewModel, navController)
                }
                composable("add") {
                    AddTodoScreen(
                        onSave = { title -> viewModel.addTodo(title) },
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}