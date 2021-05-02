package com.kkv.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var memory: String
        lateinit var question: String

        clickListeners()

    }

    private fun clickListeners() {
        val result:TextView = findViewById(R.id.result)

        val b1: Button = findViewById(R.id.one)
        val b2: Button = findViewById(R.id.two)
        val b3: Button = findViewById(R.id.three)
        val b4: Button = findViewById(R.id.four)
        val b5: Button = findViewById(R.id.five)
        val b6: Button = findViewById(R.id.six)
        val b7: Button = findViewById(R.id.seven)
        val b8: Button = findViewById(R.id.eight)
        val b9: Button = findViewById(R.id.nine)
        val b0: Button = findViewById(R.id.zero)
        val bAdd: Button = findViewById(R.id.add)
        val bSubtract: Button = findViewById(R.id.subtract)
        val bMultiply: Button = findViewById(R.id.multiply)
        val bDivide: Button = findViewById(R.id.divide)
        val bPercent: Button = findViewById(R.id.percent)
        val bDot: Button = findViewById(R.id.dot)
        val bEquals: Button = findViewById(R.id.equals)
        val bClear: Button = findViewById(R.id.clear)
        val bAC: Button = findViewById(R.id.ac)

        b1.setOnClickListener {
            result.text=result.text.toString()+b1.text.toString()
        }
        b2.setOnClickListener {
            result.text=result.text.toString()+b2.text.toString()
        }
        b3.setOnClickListener {
            result.text=result.text.toString()+b3.text.toString()
        }
        b4.setOnClickListener {
            result.text=result.text.toString()+b4.text.toString()
        }
        b5.setOnClickListener {
            result.text=result.text.toString()+b5.text.toString()
        }
        b6.setOnClickListener {
            result.text=result.text.toString()+b6.text.toString()
        }
        b7.setOnClickListener {
            result.text=result.text.toString()+b7.text.toString()
        }
        b8.setOnClickListener {
            result.text=result.text.toString()+b8.text.toString()
        }
        b9.setOnClickListener {
            result.text=result.text.toString()+b9.text.toString()
        }
        b0.setOnClickListener {
            result.text=result.text.toString()+b0.text.toString()
        }
        bAdd.setOnClickListener {
            result.text=result.text.toString()+bAdd.text.toString()
        }
        bSubtract.setOnClickListener {
            result.text=result.text.toString()+bSubtract.text.toString()
        }
        bMultiply.setOnClickListener {
            result.text=result.text.toString()+bMultiply.text.toString()
        }
        bDivide.setOnClickListener {
            result.text=result.text.toString()+bDivide.text.toString()
        }
        bPercent.setOnClickListener {
            result.text=result.text.toString()+bPercent.text.toString()
        }
        bAC.setOnClickListener {
            result.text="0"
        }
        bEquals.setOnClickListener {
            // function to execute the question var to be added in all digits and here
        }
        bClear.setOnClickListener {
            result.text=result.text.toString().take(result.text.toString().length -1)
        }
        bDot.setOnClickListener {
            result.text=result.text.toString()+bDot.text.toString()
        }

    }
}