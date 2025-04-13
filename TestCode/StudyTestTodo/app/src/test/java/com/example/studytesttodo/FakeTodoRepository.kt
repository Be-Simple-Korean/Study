package com.example.studytesttodo

import com.example.studytesttodo.domain.Todo
import com.example.studytesttodo.domain.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeTodoRepository : TodoRepository {
    private val todos = MutableStateFlow<List<Todo>>(emptyList())

    override fun getTodos(): Flow<List<Todo>> = todos

    override suspend fun addTodo(title: String) {
        val new = Todo(id = (todos.value.maxOfOrNull { it.id } ?: 0) + 1, title = title)
        todos.value += new
    }

    override suspend fun toggleTodo(todo: Todo) {
        todos.value = todos.value.map {
            if (it.id == todo.id) it.copy(isDone = !it.isDone) else it
        }
    }

    override suspend fun deleteTodo(todo: Todo) {
        todos.value = todos.value.filter { it.id != todo.id }
    }
}