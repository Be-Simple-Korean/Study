package com.example.studytesttodo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studytesttodo.data.TodoRepositoryImpl
import com.example.studytesttodo.data.db.TodoDAO
import com.example.studytesttodo.data.db.TodoDatabase
import com.example.studytesttodo.domain.Todo
import com.example.studytesttodo.domain.TodoRepository
import com.example.studytesttodo.ui.TodoViewModel
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
    private lateinit var dao: TodoDAO
    private lateinit var repository: TodoRepository
    private lateinit var viewModel: TodoViewModel


    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
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

        val todo = viewModel.todos.first { it.isNotEmpty() }
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

    /**
     * 데이터 2개 추가 후, 하나만 삭제시, 데이터 검증
     */
    @Test
    fun 상태변화추적테스트() = runTest {
        viewModel.addTodo("첫번째 데이터")
        viewModel.addTodo("두번째 데이터")

        val todo = viewModel.todos.first { it.isNotEmpty() }
        viewModel.deleteTodo(todo.first())
        val deletedList = viewModel.todos.first { it.size == 1 }

        assertEquals(1, deletedList.size)
        assertEquals("두번째 데이터", deletedList.first().title)
    }

    /**
     * 존재하지 않는 id를 가진 데이터를 toggleTodo() 호출 시,
     * 변화가 없어야 정상동작임. 이를 검증
     */
    @Test
    fun 업데이트비정상케이스검증() = runTest {
        viewModel.addTodo("토글 테스트")
        val todo = viewModel.todos.first { it.isNotEmpty() }
        val data = todo.first()
        viewModel.toggleTodo(Todo(id = 10, title = "테스트"))
        val afterList = viewModel.todos.first {
            it.any { it.id == data.id }
        }
        val after = afterList.first { it.id == data.id }
        assertEquals(data.isDone, after.isDone)
    }

    /**
     * 데이터를 여러개 추가하고, 일부만 토글 후,
     * 데이터를 가져와서 필터링 검증
     */
    @Test
    fun isDone필터링테스트() = runTest {
        viewModel.addTodo("첫번째 데이터")
        viewModel.addTodo("두번째 데이터")
        viewModel.addTodo("세번째 데이터")

        val todos = viewModel.todos.first { it.size == 3 }
        val toggled = todos.first { it.title.contains("두번째") }
        viewModel.toggleTodo(toggled)

        val doneTodos = viewModel.todos.first { list -> list.any { it.isDone } }
            .filter { it.isDone }

        assertEquals(1, doneTodos.size)

        val done = doneTodos.first()
        assertEquals(toggled.id, done.id)
        assertEquals(toggled.title, done.title)
        assertTrue(done.isDone)
    }

    @After
    fun teardown() {
        db.close()
    }
}