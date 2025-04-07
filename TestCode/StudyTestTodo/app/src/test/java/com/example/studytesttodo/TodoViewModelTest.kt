package com.example.studytesttodo

import app.cash.turbine.test
import com.example.studytesttodo.ui.TodoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {
    private lateinit var viewModel: TodoViewModel
    private lateinit var fakeRepository: FakeTodoRepository

    @Before
    fun setUp() {
        fakeRepository = FakeTodoRepository()
        viewModel = TodoViewModel(fakeRepository)
    }

    @Test
    fun `초기 상태는 빈 리스트여야 한다`() = runTest {
        viewModel.todos.test {
            val first = awaitItem()
            assertTrue(first.isEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `할 일을 추가하면 todos 상태에 포함되어야 한다`() = runTest {
        viewModel.addTodo("Test Todo")

        viewModel.todos.test {
            val result = awaitItem()
            assertTrue(result.any { it.title == "Test Todo" })
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `할 일을 삭제하면 목록에서 사라져야 한다`() = runTest {
        viewModel.addTodo("To be deleted")
        val todo = fakeRepository.getTodos().first().first()

        viewModel.deleteTodo(todo)

        viewModel.todos.test {
            val result = awaitItem()
            assertFalse(result.any { it.id == todo.id })
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `할 일을 토글하면 isDone 상태가 반전되어야 한다`() = runTest {
        viewModel.addTodo("To be toggled")
        val todo = fakeRepository.getTodos().first().first()

        viewModel.toggleTodo(todo)

        viewModel.todos.test {
            val toggled = awaitItem().first { it.id == todo.id }
            assertEquals(!todo.isDone, toggled.isDone)
            cancelAndIgnoreRemainingEvents()
        }
    }
}