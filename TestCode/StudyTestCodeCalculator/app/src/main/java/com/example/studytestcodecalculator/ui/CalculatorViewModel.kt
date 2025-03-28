package com.example.studytestcodecalculator.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.studytestcodecalculator.model.Calculator

class CalculatorViewModel : ViewModel(){

    private val calculator  = Calculator()

    var result by mutableStateOf<Double?>(null)
        private set

    fun calculate(a:String, b:String, op: Operation){
        val numA = a.toDoubleOrNull()
        val numB = b.toDoubleOrNull()

        if (numA == null || numB == null) {
            result = null
            return
        }

        result = try {
            when (op) {
                Operation.ADD -> calculator.add(numA, numB)
                Operation.SUB -> calculator.subtract(numA, numB)
                Operation.MULTIPLE -> calculator.multiply(numA, numB)
                Operation.DIVIDE -> calculator.divide(numA, numB)
            }
        } catch (e: Exception) {
            null
        }

    }
}

