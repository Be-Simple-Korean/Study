package com.example.studytesttodo.data

import com.example.studytesttodo.data.db.TodoDAO
import com.example.studytesttodo.data.db.TodoEntity
import com.example.studytesttodo.domain.Todo
import com.example.studytesttodo.domain.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl (private val dao:TodoDAO) : TodoRepository {
    override fun getTodos(): Flow<List<Todo>> = dao.getAllTodos().map { list -> list.map { it.toModel() } }

    override suspend fun addTodo(title: String) = dao.insertTodo(TodoEntity(title=title))

    override suspend fun toggleTodo(todo: Todo) = dao.updateTodo(todo.copy(isDone = !todo.isDone).toEntity())

    override suspend fun deleteTodo(todo: Todo) = dao.deleteTodo(todo.toEntity())

}