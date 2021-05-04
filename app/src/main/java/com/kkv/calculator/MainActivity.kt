package com.kkv.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var new:Boolean = true
    var operator: Boolean =false
    var opList= mutableListOf<String>()
    var numList= mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var memory: String
        lateinit var question: String

        clickListeners()

    }

    private fun clickListeners() {

        val result:TextView = findViewById(R.id.result)
        val exp:TextView = findViewById(R.id.expression)

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
            exp.text=outText(b1.text.toString())
        }
        b2.setOnClickListener {
            exp.text=outText(b2.text.toString())
        }
        b3.setOnClickListener {
            exp.text=outText(b3.text.toString())
        }
        b4.setOnClickListener {
            exp.text=outText(b4.text.toString())
        }
        b5.setOnClickListener {
            exp.text=outText(b5.text.toString())
        }
        b6.setOnClickListener {
            exp.text=outText(b6.text.toString())
        }
        b7.setOnClickListener {
            exp.text=outText(b7.text.toString())
        }
        b8.setOnClickListener {
            exp.text=outText(b8.text.toString())
        }
        b9.setOnClickListener {
            exp.text=outText(b9.text.toString())
        }
        b0.setOnClickListener {
            exp.text=outText(b0.text.toString())
        }
        bAdd.setOnClickListener {
            exp.text=operatorFun(bAdd.text.toString())
        }
        bSubtract.setOnClickListener {
            exp.text=operatorFun(bSubtract.text.toString())
        }
        bMultiply.setOnClickListener {
            exp.text=operatorFun(bMultiply.text.toString())
        }
        bDivide.setOnClickListener {
            exp.text=operatorFun(bDivide.text.toString())
        }
        bPercent.setOnClickListener {
            exp.text=operatorFun(bPercent.text.toString())
        }
        bAC.setOnClickListener {
            result.text="0"
            exp.text=""
            new=true
            operator=false
            opList.clear()
            numList.clear()
        }
        bEquals.setOnClickListener {
            // function to execute the question var to be added in all digits and here
        }
        bClear.setOnClickListener {
            var c1=exp.text.toString()
            if (c1=="")
                return@setOnClickListener
            if (c1.last() in arrayOf('+','—','x','÷','%')){
                opList.removeLast()
                new=false
                Log.d("Main","oplist is updated by clear button ${opList}")
            }
            else if (c1.last() in arrayOf('1','2','3','4','5','6','7','8','9','0')){
                var temp:String =numList.last()
                temp=temp.take(temp.length-1)
                numList.removeLast()
                if (temp != ""){
                    numList.add(temp)
                }
                new=true

                Log.d("Main","numList updated from clear ${numList}")
            }
            if (c1.length == 1) {
                exp.text =""
            }
            else{
                exp.text = c1.take(c1.length - 1)
            }
            operator=false

        }
        bDot.setOnClickListener {
            exp.text=exp.text.toString()+bDot.text.toString()
        }
    }

    private fun operatorFun(inputText: String): CharSequence? {
        val result:TextView = findViewById(R.id.result)
        val exp:TextView = findViewById(R.id.expression)
        new=true
        if (!operator){
            operator = true
            opList.add((inputText))
            Log.d("Main","oplist is updated: ${opList}")
            return exp.text.toString()+inputText
        }
        else{
            opList.removeLast()
            opList.add(inputText)
            Log.d("Main","oplist else updated: ${opList}")
            return exp.text.toString().take(exp.text.length - 1) + inputText
        }

    }

    private fun outText(inputText: String): CharSequence {
        val result:TextView = findViewById(R.id.result)
        val exp:TextView = findViewById(R.id.expression)
        if (new){
            numList.add(inputText)
            Log.d("Main","numList updated though adding ${numList}")
            new=false
        }
        else{
            var useless:String=""
            useless=numList.last()+inputText
            numList.removeLast()
            numList.add(useless)
            Log.d("Main","numList updated though adding in else ${numList}")
        }
        operator=false
        val temp : String = exp.text.toString()+inputText
        if(exp.text.toString().take(1) == "0"){
            return temp.takeLast(temp.length -1)
        }
        return temp
    }
}
