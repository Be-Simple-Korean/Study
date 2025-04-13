package com.example.studytesttodo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.studytesttodo.data.db.TodoDAO
import com.example.studytesttodo.data.db.TodoDatabase
import com.example.studytesttodo.data.db.TodoEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class TodoDBTest {

    private lateinit var db: TodoDatabase
    private lateinit var dao : TodoDAO

    @Before
    fun setUp(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.todoDao()
    }

    @Test
    fun `할 일을 추가하면 전체 목록에서 확인할 수 있어야 한다`() = runTest{
        val todo = TodoEntity(title = "룸 테스트")
        dao.insertTodo(todo)

        val result = dao.getAllTodos().first()
        assertEquals(1,result.size)
        assertEquals("룸 테스트",result.first().title)
    }

    @Test
    fun `할 일을 토글하면 isDone이 반전되어야 한다`() = runTest {
        val todo = TodoEntity(title = "업데이트 테스트")
        dao.insertTodo(todo)

        val inserted = dao.getAllTodos().first().first()
        val updated = inserted.copy(isDone = true)

        dao.updateTodo(updated)
        val result = dao.getAllTodos().first().first()

        assertTrue(result.isDone)
    }

    @Test
    fun `할 일을 삭제하면 목록에서 사라져야 한다`() = runTest {
        val todo = TodoEntity(title = "삭제 테스트")
        dao.insertTodo(todo)

        val inserted = dao.getAllTodos().first().first()
        dao.deleteTodo(inserted)

        val result = dao.getAllTodos().first()
        assertTrue(result.isEmpty())
    }

    @After
    fun teardown() {
        db.close()
    }
}