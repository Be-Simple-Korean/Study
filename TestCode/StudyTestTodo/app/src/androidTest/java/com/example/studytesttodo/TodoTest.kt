package com.example.studytesttodo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studytesttodo.data.TodoRepositoryImpl
import com.example.studytesttodo.data.db.TodoDAO
import com.example.studytesttodo.data.db.TodoDatabase
import com.example.studytesttodo.data.db.TodoEntity
import com.example.studytesttodo.domain.TodoRepository
import com.example.studytesttodo.ui.TodoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
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
    private lateinit var viewModel: TodoViewModel


    @Before
    fun setUp(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.todoDao()
        repository = TodoRepositoryImpl(dao)
        viewModel = TodoViewModel(repository)
    }

    @Test
    fun addTest() = runTest {
        viewModel.addTodo("뷰모델 테스트")
        val result = viewModel.todos.first { it.isNotEmpty() }

        assertEquals(1, result.size)
        assertEquals("뷰모델 테스트", result.first().title)
    }

    @Test
    fun updateTest() = runTest {
        viewModel.addTodo("토글 테스트")

        val todo = viewModel.todos.first{it.isNotEmpty()}
        val data = todo.first()
        viewModel.toggleTodo(data)
        val updatedList = viewModel.todos.first { list ->
            list.any { it.id == data.id && it.isDone != data.isDone }
        }
        val updated = updatedList.first { it.id == data.id }

        assertEquals(!data.isDone, updated.isDone)
    }

    @Test
    fun deleteTest() = runTest {
        viewModel.addTodo("삭제 테스트")
        val todo = viewModel.todos.first { it.isNotEmpty() }

        viewModel.deleteTodo(todo.first())

        val deletedList = viewModel.todos.first { it.isEmpty() } // ✅ 조건 변경
        assertTrue(deletedList.none { it.id == todo.first().id })
    }

    @After
    fun teardown() {
        db.close()
    }
}