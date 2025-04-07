package com.example.studytesttodo.data

import com.example.studytesttodo.data.db.TodoEntity
import com.example.studytesttodo.domain.Todo

fun TodoEntity.toModel(): Todo = Todo(id = id, title = title, isDone = isDone)
fun Todo.toEntity(): TodoEntity = TodoEntity(id = id, title = title, isDone = isDone)