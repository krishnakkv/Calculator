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
    var opMap= mutableMapOf("÷" to 0,"x" to 0,"+" to 0,"—" to 0)
    var numList= mutableListOf<String>()
    var zeroError=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Main","opMap test at start $opMap ${opMap.keys}")

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
            // need to divide the number by 100
            if (numList.isNotEmpty()){
                var temp1=numList.last()
                Log.d("Main","Percent clicked last value of numList is $temp1")
                numList.remove(temp1)
                temp1="${temp1.toDouble()/100.0}"
                numList.add(temp1)
                result.text=calc()

            }
        }
        bAC.setOnClickListener {
            result.text="0"
            exp.text=""
            new=true
            operator=false
            zeroError=false
            opList.clear()
            numList.clear()
            opMap.clear()
            opMap["÷"] = 0
            opMap["x"] = 0
            opMap["+"] = 0
            opMap["—"] = 0
        }
        bEquals.setOnClickListener {
            // function to execute the question var to be added in all digits and here
            result.text=calc()
        }
        bClear.setOnClickListener {
            val c1=exp.text.toString()
            if (c1=="")
                return@setOnClickListener
            if (c1.last() in arrayOf('+','—','x','÷')){
                val tempR:Int = opMap[opList.last()]!!
                opMap[opList.last()]=tempR - 1
                opList.removeLast()
                new=false
                Log.d("Main","oplist is updated by clear button $opList")
                Log.d("Main","opMap updated $opMap")
            }
            else if (c1.last() in arrayOf('1','2','3','4','5','6','7','8','9','0')){
                var temp:String =numList.last()
                temp=temp.take(temp.length-1)
                numList.removeLast()
                if (temp != ""){
                    numList.add(temp)
                }
                else {
                    new = true
                }
                Log.d("Main","numList updated from clear $numList")
                Log.d("Main","opMap updated $opMap")
            }
            if (c1.length == 1) {
                exp.text =""
                result.text ="0"
            }
            else{
                exp.text = c1.take(c1.length - 1)
                result.text=calc()
            }
            operator = new
        }
        bDot.setOnClickListener {
            exp.text=exp.text.toString()+bDot.text.toString()
        }
    }

    private fun operatorFun(inputText: String): CharSequence {
        val exp:TextView = findViewById(R.id.expression)
        if (numList.isEmpty()){
            Log.d("Main","Operator used without number! entering 0")
            numList.add("0")
            exp.text="0"
        }
        new=true
        if (!operator){
            val temp: Int = opMap[inputText]?:0
            operator = true
            opList.add((inputText))
            Log.d("Main","oplist is updated: $opList")
            opMap[inputText]= temp +1
            Log.d("Main","opMap updated $opMap")
            return exp.text.toString()+inputText
        }
        else{
            val tempR:Int = opMap[opList.last()]!!
            opMap[opList.last()]=tempR - 1
            opList.removeLast()
            val temp: Int = opMap[inputText]?:0
            opList.add(inputText)
            opMap[inputText]=temp +1
            Log.d("Main","oplist else updated: $opList")
            Log.d("Main","opMap updated $opMap")
            return exp.text.toString().take(exp.text.length - 1) + inputText
        }

    }

    private fun outText(inputText: String): CharSequence {
        val result:TextView = findViewById(R.id.result)
        val exp:TextView = findViewById(R.id.expression)
        if (new){
            numList.add(inputText)
            Log.d("Main","numList updated though adding $numList")
            new=false
        }
        else{
            val useless: String = numList.last()+inputText
            numList.removeLast()
            numList.add(useless)
            Log.d("Main","numList updated though adding in else $numList")
        }
        operator=false
        val temp : String = exp.text.toString()+inputText
        if(exp.text.toString().take(1) == "0" && opList.isEmpty()){
            return temp.takeLast(temp.length -1)
        }
        result.text=calc()
        return temp
    }

    private fun calc(): String {
        val tempOpMap = opMap.toMutableMap()
        val tempNumList = numList.toMutableList()
        val tempOpList = opList.toMutableList()
        if (opList.size == numList.size){
            val del=tempOpMap[tempOpList.last()]
            tempOpMap[tempOpList.last()]=(del?:1)-1
            tempOpList.removeLast()
        }
        for(key in tempOpMap.keys){
            Log.d("Main","Iterating through the keys $key")
            if (tempOpMap[key]!=0){
                var countIndex = 0
                while (tempOpMap[key]!=0) {
                    if (tempOpList[countIndex] != key) {
                        countIndex += 1
                    } else {
                        Log.d("Main", "operator $key is at index $countIndex")
                        val result = calculate(tempNumList[countIndex].toDouble(), tempNumList[countIndex + 1].toDouble(), tempOpList[countIndex])
                        if (zeroError){
                            return "division by Zero!"
                        }
                        tempOpList.removeAt(countIndex)
                        tempNumList.removeAt(countIndex)
                        tempNumList[countIndex] = result
                        Log.d("Main", "after removal of element the temp list are $tempNumList and $tempOpList")
                        Log.d("Main", "after removal of element the main list are $numList and $opList")
                        val tempOp: Int = tempOpMap[key]?:1
                        tempOpMap[key] = tempOp - 1
                    }
                }
            }
        }
        return tempNumList[0]
    }

    private fun calculate(n1:Double,n2:Double, op:String): String{
        when (op) {
            "÷" -> {
                Log.d("Main","calculated the value for division ")
                if (n2==0.0){
                    zeroError=true
                    return 1.toString()
                }
                return ((n1/n2).toString())
            }
            "x" -> {
                Log.d("Main","calculated the value for multiplication ${n1 * n2}")
                return "${n1*n2}"
            }
            "+" -> {
                Log.d("Main","calculated the value for Addition ${n1 + n2}")
                return "${n1+n2}"
            }
            "—" -> {
                Log.d("Main","calculated the value for Substraction ${n1 - n2}")
                return "${n1-n2}"
            }
            else -> return ""
        }
    }
}
