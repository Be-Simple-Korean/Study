package com.example.studytesttodo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studytesttodo.domain.Todo
import com.example.studytesttodo.domain.TodoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    val todos: StateFlow<List<Todo>> = repository.getTodos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTodo(title: String) {
        viewModelScope.launch {
            repository.addTodo(title)
        }
    }

    fun toggleTodo(todo: Todo) {
        viewModelScope.launch {
            repository.toggleTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }
}