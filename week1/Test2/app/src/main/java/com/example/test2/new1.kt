package com.example.test2

fun main(){
    var n=11
    paint(n)
}

fun paint(n:Int){
    var _n:Int = n/2+1
    //println(_n)
    for(i in 1.._n){
        for(j in 1.._n-i){
            print(" ")
        }
        for(j in 1..2*i-1){
            print("*")
        }
        println()
    }
    for(i in _n-1 downTo 1){
        for(j in 1.._n-i){
            print(" ")
        }
        for(j in 1..2*i-1){
            print("*")
        }
        println()
    }
}