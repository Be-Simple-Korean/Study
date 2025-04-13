package com.example.studytesttodo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studytesttodo.data.TodoRepositoryImpl
import com.example.studytesttodo.data.db.TodoDAO
import com.example.studytesttodo.data.db.TodoDatabase
import com.example.studytesttodo.data.db.TodoEntity
import com.example.studytesttodo.domain.TodoRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoTest {

    private lateinit var db: TodoDatabase
    private lateinit var dao : TodoDAO
    private lateinit var repository: TodoRepository


    @Before
    fun setUp(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.todoDao()
        repository = TodoRepositoryImpl(dao)
    }

    @Test
    fun addTest() = runTest {
        val todo = TodoEntity(title = "룸 테스트")
        dao.insertTodo(todo)

        val result = dao.getAllTodos().first()
        assertEquals(1, result.size)
        assertEquals("룸 테스트", result.first().title)
    }

    @Test
    fun updateTest() = runTest {
        repository.addTodo("토글 대상")
        val todo = repository.getTodos().first().first()

        repository.toggleTodo(todo)

        val updated = repository.getTodos().first().first { it.id == todo.id }
        assertEquals(!todo.isDone, updated.isDone)
    }

    @Test
    fun deleteTest() = runTest {
        repository.addTodo("삭제 대상")
        val todo = repository.getTodos().first().first()

        repository.deleteTodo(todo)

        val list = repository.getTodos().first()
        assertTrue(list.none { it.id == todo.id })
    }

    @After
    fun teardown() {
        db.close()
    }
}