package com.example.studytestcodecalculator

import com.example.studytestcodecalculator.ui.CalculatorViewModel
import com.example.studytestcodecalculator.ui.Operation
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class CalculatorViewModelTest {

    private lateinit var viewModelTest: CalculatorViewModel

    @Before
    fun setUp(){
        viewModelTest = CalculatorViewModel()
    }

    @Test
    fun add_twoNumbers_returnsCorrectSum(){
        viewModelTest.calculate("3","4",Operation.ADD)
        assertEquals(7.0,viewModelTest.result ?: 0.0,0.0)
    }

    @Test
    fun divide_byZero_returnsNull(){
        viewModelTest.calculate("10","0",Operation.DIVIDE)
        assertNull(viewModelTest.result)
    }

    @Test
    fun invalidInput_returnsNull(){
        viewModelTest.calculate("abc","4",Operation.ADD)
        assertNotNull(viewModelTest.result)
    }
}