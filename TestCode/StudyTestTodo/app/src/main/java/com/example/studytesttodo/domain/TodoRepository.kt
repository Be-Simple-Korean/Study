package com.example.studytesttodo.domain

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>
    suspend fun addTodo(title: String)
    suspend fun toggleTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
}
