package com.example.test1

fun main(){
    var num1 = 60000.0
    println("应纳税: ${calc(num1)}")
}

fun calc(num1:Double):Double{
    var tax:Double = 0.0
    var _tax:Double = 0.0
    if(num1 < 4000){
        tax = (num1 - 800) * 0.2
    }
    else{
        _tax = num1 * 0.8
        if(_tax < 20000){
            tax = _tax * 0.2
        }
        else if (20000 < _tax && _tax < 50000){
            tax = _tax * 0.3 - 2000
        }
        else{
            tax = _tax * 0.4 - 7000
        }
    }
    return tax
}