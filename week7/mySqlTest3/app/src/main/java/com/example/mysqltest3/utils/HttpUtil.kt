package com.example.mysqltest3.utils

import com.example.mysqltest3.databinding.ActivityNetWorkBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object HttpUtil {
    //回调参数 listener
    fun sendByHttpUrlConn(binding: ActivityNetWorkBinding,url:String,listener:HttpCallbackListener) {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val url = URL(url)
                connection = url.openConnection() as HttpURLConnection //打开链接
                connection.connectTimeout = 8000 //8000毫秒，链接超时
                connection.readTimeout = 8000 //读取超时

                val input = connection.inputStream //输入流
                val builder = StringBuilder()
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        builder.append(it + "\n")
                    }
                }
                //网络正常
                listener.onFinish(builder.toString())

            } catch (e: Exception) {
                //中间可能会阻塞导致异常
                //打印异常
                e.printStackTrace()
                //网络失败
                listener.onError(e)
            } finally {
                //最后断开连接
                connection?.disconnect()
            }
        }
    }

    fun sendByOkHttp(binding: ActivityNetWorkBinding, url:String, callback:okhttp3.Callback){
        val client = OkHttpClient()
        //构建请求
        val request = Request.Builder()
            .url(url)
            .build()
        //执行
        client.newCall(request).enqueue(callback) //隐含了thread, try
    }
}