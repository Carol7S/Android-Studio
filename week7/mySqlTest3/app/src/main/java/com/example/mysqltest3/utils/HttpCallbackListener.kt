package com.example.mysqltest3.utils

interface HttpCallbackListener {
    //正常
    fun onFinish(response:String)
    //失败
    fun onError(e:Exception)
}