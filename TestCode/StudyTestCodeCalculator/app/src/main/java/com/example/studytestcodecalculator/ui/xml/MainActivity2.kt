package com.example.studytestcodecalculator.ui.xml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studytestcodecalculator.R
import com.example.studytestcodecalculator.databinding.ActivityMain2Binding
import com.example.studytestcodecalculator.ui.CalculatorViewModel
import com.example.studytestcodecalculator.ui.Operation

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private val viewModel by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener { calculate(Operation.ADD) }
        binding.btnSub.setOnClickListener { calculate(Operation.SUB) }
        binding.btnMul.setOnClickListener { calculate(Operation.MULTIPLE) }
        binding.btnDiv.setOnClickListener { calculate(Operation.DIVIDE) }

        viewModel.result2.observe(this) { result ->
            binding.textResult.text = "Result: $result"
        }
    }

    private fun calculate(op: Operation) {
        val a = binding.inputA.text.toString()
        val b = binding.inputB.text.toString()
        viewModel.calculate(a, b, op)
    }
}