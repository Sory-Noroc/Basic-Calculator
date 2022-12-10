package org.hyperskill.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        var previousNumber: Double? = null
        var operation: Operation? = null

        fun cleanDouble(num: Double): String {
            val numString = num.toString()
            return if (numString.endsWith(".0")) {
                numString.dropLast(2)
            } else {
                numString
            }
        }

        fun addNumber(number: Int){
            if(editText.text.toString() == "0") {
                editText.setText(number.toString())
            } else {
                editText.text.append(number.toString())
            }
        }

        fun getAndSetZero(): Double? {
            // Returns null if couldn't convert to double
            val d = editText.text.toString().toDoubleOrNull()
            editText.setText("0")

            return d
        }

        findViewById<Button>(R.id.subtractButton).setOnClickListener {
            if (editText.text.toString() in listOf("0", "0.")) {
                editText.setText("-")
            } else {
                // If the editText is not zero
                previousNumber = getAndSetZero()
                operation = Operation.SUBTRACT
                editText.setText("")
                editText.hint = cleanDouble(previousNumber ?: 0.0)
            }
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            previousNumber = getAndSetZero()
            operation = Operation.ADD
            editText.setText("")
            editText.hint = cleanDouble(previousNumber ?: 0.0)
        }

        findViewById<Button>(R.id.multiplyButton).setOnClickListener {
            previousNumber = getAndSetZero()
            operation = Operation.MULTIPLY
            editText.setText("")
            editText.hint = cleanDouble(previousNumber ?: 0.0)
        }

        findViewById<Button>(R.id.divideButton).setOnClickListener {
            previousNumber = getAndSetZero()
            operation = Operation.DIVIDE
            editText.setText("")
            editText.hint = cleanDouble(previousNumber ?: 0.0)
        }

        findViewById<Button>(R.id.equalButton).setOnClickListener {
            val currentNumber = editText.text.toString().toDoubleOrNull()
            var result: Double? = null

            when(operation) {
                Operation.ADD -> {
                    result = currentNumber?.let { previousNumber?.plus(it) }
                }
                Operation.SUBTRACT -> {
                    result = currentNumber?.let { previousNumber?.minus(it) }
                }
                Operation.MULTIPLY -> {
                    result = currentNumber?.let { previousNumber?.times(it) }
                }
                Operation.DIVIDE -> {
                    result = currentNumber?.let { previousNumber?.div(it) }
                }
                else -> {}
            }
            result?.run {
                val stringResult = cleanDouble(result)

                editText.setText(stringResult)
                operation = null
            }
        }

        findViewById<Button>(R.id.dotButton).setOnClickListener {
            if (editText.text.toString() == "-") {
                editText.append("0.")
            } else if (!editText.text.contains('.')) {
                editText.append(".")
            }
        }

        findViewById<Button>(R.id.clearButton).setOnClickListener {
            editText.hint = "0"
            editText.setText("0")
        }

        findViewById<Button>(R.id.button0).setOnClickListener {
            if(editText.text.toString() !in listOf("0", "-")) {
                editText.text.append("0")
            }
            // if it's zero do nothing
        }

        findViewById<Button>(R.id.button1).setOnClickListener {
            addNumber(1)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            addNumber(2)
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            addNumber(3)
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            addNumber(4)
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            addNumber(5)
        }

        findViewById<Button>(R.id.button6).setOnClickListener {
            addNumber(6)
        }

        findViewById<Button>(R.id.button7).setOnClickListener {
            addNumber(7)
        }

        findViewById<Button>(R.id.button8).setOnClickListener {
            addNumber(8)
        }

        findViewById<Button>(R.id.button9).setOnClickListener {
            addNumber(9)
        }
    }
}