package com.example.test1

import java.io.Serializable
//序列化
//data class TrainInfo(val val startStation:String, val endStation:String, val secondNum:Int):Serializable
//data class TrainInfo(val name:String, val startStation:String, val endStation:String, val startTime:String, val endTime:String)
data class TrainInfo(val name:String, val startStation:String, val endStation:String, val startTime:String, val endTime:String, val shangWu:Int, val firstNumber:Int, val secondNumber:Int, val wuZuo:Int)
