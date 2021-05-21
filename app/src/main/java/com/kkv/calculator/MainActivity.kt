package com.kkv.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.math.BigDecimal


class MainActivity : AppCompatActivity() {

    private var new:Boolean = true
    private var operator: Boolean =false
    private var opList= mutableListOf<String>()
    private var opMap= mutableMapOf("÷" to 0,"x" to 0,"+" to 0,"—" to 0)
    private var numList= mutableListOf<String>()
    private var zeroError=false
    private var dot=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Main","opMap test at start $opMap ${opMap.keys}")

        clickListeners()

    }

    private fun clickListeners() {

        val result:TextView = findViewById(R.id.result)
        val expression:TextView = findViewById(R.id.expression)

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
            outText(b1.text.toString())
        }
        b2.setOnClickListener {
            outText(b2.text.toString())
        }
        b3.setOnClickListener {
            outText(b3.text.toString())
        }
        b4.setOnClickListener {
            outText(b4.text.toString())
        }
        b5.setOnClickListener {
            outText(b5.text.toString())
        }
        b6.setOnClickListener {
            outText(b6.text.toString())
        }
        b7.setOnClickListener {
            outText(b7.text.toString())
        }
        b8.setOnClickListener {
            outText(b8.text.toString())
        }
        b9.setOnClickListener {
            outText(b9.text.toString())
        }
        b0.setOnClickListener {
            if (numList.isEmpty() || new || numList.last()!="0") {
                outText(b0.text.toString())
            }
        }
        bAdd.setOnClickListener {
            operatorFun(bAdd.text.toString())
        }
        bSubtract.setOnClickListener {
            operatorFun(bSubtract.text.toString())
        }
        bMultiply.setOnClickListener {
            operatorFun(bMultiply.text.toString())
        }
        bDivide.setOnClickListener {
            operatorFun(bDivide.text.toString())
        }
        bPercent.setOnClickListener {
            // need to divide the number by 100
            if (numList.isNotEmpty() && !operator){
                var temp1=numList.last().toBigDecimal()
                Log.d("Main","Percent clicked last value of numList is $temp1")
                numList.removeLast()
                temp1 = temp1.divide(100.toBigDecimal())
                Log.d("Main","The temp contains $temp1")
                numList.add("$temp1")
                dot=('.' in temp1.toString())
                result.text=calc()
                expression.text=getExp()
            }
        }
        bAC.setOnClickListener {
            result.text="0"
            expression.text=""
            new=true
            operator=false
            zeroError=false
            dot=false
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
            if (numList.size==0)
                return@setOnClickListener
            val c1=expression.text
            if (operator){
                val tempR:Int = opMap[opList.last()]!!
                opMap[opList.last()]=tempR - 1
                opList.removeLast()
                new=false
                Log.d("Main","opList is updated by clear button $opList")
                Log.d("Main","opMap updated $opMap")
                dot = '.' in numList.last()
            }
            else{
                var temp: String = numList.last()
                Log.d("Main","the value in temp is $temp")
                temp = temp.take(temp.length - 1)
                numList.removeLast()
                if (temp != "") {
                    numList.add(temp)
                    dot='.' in temp
                } else {
                    new = true
                }
                Log.d("Main","numList updated from clear $numList")
                Log.d("Main","opMap updated $opMap")

            }

            if (c1.length == 1) {
                expression.text=""
                result.text ="0"
            }
            else{
                expression.text=getExp()
                result.text=calc()
            }
            operator = new
        }
        bDot.setOnClickListener {
            if (!dot){
                if (new){
                    numList.add("0")
                    new=false
                }
                var temp=numList.last()
                temp+="."
                dot=true
                numList.removeLast()
                numList.add(temp)
                expression.text=getExp()
            }
        }
    }

    private fun getExp(): CharSequence {
        var count=0
        var tempExp=""
        while (true){
            if (numList.size<=count || numList.size==0){
                Log.d("Main","Loop breaks at $count")
                break
            }
            Log.d("Main","loop $count , $tempExp")
            tempExp += numList[count]
            if (opList.size<=count || opList.size==0){
                Log.d("Main","Loop breaks at $count")
                break
            }
            Log.d("Main","loop $count , $tempExp")
            tempExp += opList[count]
            count+=1
        }
        return tempExp
    }

    private fun operatorFun(inputText: String) {
        val expression:TextView = findViewById(R.id.expression)
        if (numList.isEmpty()){
            Log.d("Main","Operator used without number! entering 0")
            numList.add("0")
        }
        new=true
        dot=false
        if (!operator){
            val temp: Int = opMap[inputText]?:0
            operator = true
            opList.add((inputText))
            Log.d("Main","oplist is updated: $opList")
            opMap[inputText]= temp +1
            Log.d("Main","opMap updated $opMap")
            expression.text=getExp()
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
            expression.text=getExp()
        }

    }

    private fun outText(inputText: String) {
        val result:TextView = findViewById(R.id.result)
        val expression:TextView = findViewById(R.id.expression)
        if (new){
            numList.add(inputText)
            Log.d("Main","numList updated though adding $numList")
            new=false
        }
        else{
            if (inputText!="0"){
                zeroError=false
            }
            val useless: String = numList.last()+inputText
            numList.removeLast()
            numList.add(useless)
            Log.d("Main","numList updated though adding in else $numList")
        }
        operator=false
        result.text=calc()
        expression.text=getExp()
    }

    private fun calc(): String {
        val tempOpMap = opMap.toMutableMap()
        val tempNumList = numList.toMutableList()
        val tempOpList = opList.toMutableList()
        if (numList.isEmpty()){
            return "0"
        }
        if (opList.size == numList.size){
            val del=tempOpMap[tempOpList.last()]
            tempOpMap[tempOpList.last()]=(del?:1)-1
            tempOpList.removeLast()
        }
        for(key in tempOpMap.keys){
            Log.d("Main","Iterating through the keys $key")
            if (tempOpMap[key]!=0){
                var countIndex = 0
                var opPrevious = ""
                while (tempOpMap[key]!=0) {
                    if (tempOpList[countIndex] != key) {
                        countIndex += 1
                    } else {
                        Log.d("Main", "operator $key is at index $countIndex")
                        if (countIndex>0){
                            opPrevious=tempOpList[countIndex-1]
                        }
                        val result = calculate(tempNumList[countIndex].toBigDecimal(), tempNumList[countIndex + 1].toBigDecimal(), tempOpList[countIndex],opPrevious)
                        if (zeroError){
                            return "Division by Zero!"
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
        var zeroRemove=tempNumList[0]
        if ('.' in zeroRemove){
            while (zeroRemove.last()=='0') {
                zeroRemove = zeroRemove.take(zeroRemove.length - 1)
            }
            if (zeroRemove.last()=='.'){
                zeroRemove = zeroRemove.take(zeroRemove.length - 1)
            }
        }
        return zeroRemove
    }

    private fun calculate(n1:BigDecimal,n2:BigDecimal, op:String,op0:String): String{
        when (op) {
            "÷" -> {
                Log.d("Main","calculated the value for division ")
                var div = try {
                    (n1.divide(n2, 8, BigDecimal.ROUND_HALF_UP)).toString()
                }catch (exception: ArithmeticException){
                    zeroError=true
                    return ""
                }
                Log.d("Main","the value in division is $div")
                if ('.' in div) {
                    while (div.last() == '0') {
                        div = div.take(div.length - 1)
                    }
                    if (div.last() == '.'){
                        div = div.take(div.length - 1)
                    }
                }
                return div
            }
            "x" -> {
                Log.d("Main","calculated the value for multiplication ${n1 * n2}")
                return "${n1.multiply(n2)}"
            }
            "+" -> {
                if (op0=="—"){
                    if (n2>n1){
                        Log.d("Main","calculated the value for Addition with -ve n2>n1  -${(n2-n1)}")
                        return "-${n2-n1}"
                    }
                    Log.d("Main","calculated the value for Addition with -ve ${n1-n2}")
                    return "${(n1-n2)}"
                }
                Log.d("Main","calculated the value for Addition ${n1 + n2}")
                return "${n1+n2}"
            }
            "—" -> {
                Log.d("Main","calculated the value for Subtraction ${n1 - n2}")
                return "${n1-n2}"
            }
            else -> return ""
        }
    }
}
