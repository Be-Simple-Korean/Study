package com.example.studytesttodo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todos")
    fun getAllTodos(): Flow<List<TodoEntity>>
    @Insert
    suspend fun insertTodo(todo:TodoEntity)
    @Update
    suspend fun updateTodo(todo:TodoEntity)
    @Delete
    suspend fun deleteTodo(todo:TodoEntity)
}