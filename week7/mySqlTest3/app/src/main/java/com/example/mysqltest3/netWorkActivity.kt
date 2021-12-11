package com.example.mysqltest3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import com.example.mysqltest3.data.Info
import com.example.mysqltest3.databinding.ActivityNetWorkBinding
import com.example.mysqltest3.utils.HttpCallbackListener
import com.example.mysqltest3.utils.HttpUtil
import okhttp3.*
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import kotlin.concurrent.thread

class netWorkActivity : AppCompatActivity() {

    val updateText = 1
    val server_ip = "192.168.1.100"
    val test_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/test.jsp"
    val get_all_book_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/get_all_book.jsp"
    val add_book_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/add_book.jsp"
    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            //super.handleMessage(msg)
            when (msg.what) {
                updateText -> {
                    val binding = ActivityNetWorkBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    val arg1 = msg.arg1
                    val arg2 = msg.arg2
                    val info = msg.obj
                    binding.editText.setText("this is thread!,${arg1}--${arg2}--${info}")
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_work)
        val binding = ActivityNetWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //启动一个线程
        binding.btnThread.setOnClickListener() {
            thread {
                //binding.editText.setText("this is thread") //线程里不允许操作ui
//                val msg = Message() //消息
//                msg.what = updateText //消息类型
//                msg.arg1 = 100
//                msg.arg2 = 10
////                val str = "test 123"
////                msg.obj = str
//                val info = Info("杭州","上海")
//                msg.obj = info
//                handler.sendMessage(msg) //发送消息
                runOnUiThread {
                    //原理和handler一样，这个更直观、更简洁
                    binding.editText.setText("wowowo!!!Run!")
                }
            }
        }

        binding.btnHttpURLCon.setOnClickListener() {
            thread {
                var connection: HttpURLConnection? = null
                try {
                    val url = URL(get_all_book_url)
                    connection = url.openConnection() as HttpURLConnection //打开链接
                    connection.connectTimeout = 8000 //8000毫秒，链接超时
                    connection.readTimeout = 8000 //读取超时

//                connection.setRequestProperty("Accept-Charset","UTF-8")
//                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf-8")
//                connection.requestMethod="POST"
//                val output = DataOutputStream(connection.outputStream)
//                val paraStr = "name=晋书&author=房玄龄&pages=1200&price=185"
//                output.write(paraStr.toByteArray())//转换成字符数组写进去

                    val input = connection.inputStream //输入流
                    val builder = StringBuilder()
                    val reader = BufferedReader(InputStreamReader(input))
                    reader.use {
                        reader.forEachLine {
                            builder.append(it+"\n")
                        }
                    }

                    runOnUiThread {
                        binding.tvResponse.text = builder.toString()
                    }
                } catch (e: Exception) {
                    //中间可能会阻塞导致异常
                    //打印异常
                    e.printStackTrace()
                } finally {
                    //最后断开连接
                    connection?.disconnect()
                }
            }
        }

        binding.btnOkHttp.setOnClickListener(){
            getByOkHttp(binding,test_url)
        }

        // HttpUrlConn回调
        binding.btnHttpUrlConnCallback.setOnClickListener(){
            HttpUtil.sendByHttpUrlConn(binding,get_all_book_url,object:HttpCallbackListener{
                override fun onFinish(response: String) {
                    runOnUiThread(){
                        binding.tvResponse.text = response
                    }
                }

                override fun onError(e: Exception) {
                    e.printStackTrace()
                }
            })
        }

        binding.btnOkHttpCallback.setOnClickListener(){
            HttpUtil.sendByOkHttp(binding,get_all_book_url,object : Callback{
                override fun onResponse(call: Call, response: Response) {
                    val responseData = response.body?.string()
                    runOnUiThread(){
                        binding.tvResponse.text = responseData
                    }
                }
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }
            })
        }
    }


    private fun getByOkHttp(binding: ActivityNetWorkBinding,url:String){
        thread {
            try {
                val client = OkHttpClient()
                //构建数据
                val requestBody = FormBody.Builder()
                    .add("name",URLEncoder.encode("新唐书","utf-8"))
                    .add("author",URLEncoder.encode("欧阳修","utf-8"))
                    .add("pages","1234")
                    .add("price","152.4")
                    .build()
                //构建请求
                val request = Request.Builder()
                    .url(url)
                    .build()

//                val request = Request.Builder()
//                    .url(add_book_url)
//                    .post(requestBody)
//                    .build()

                //执行
                val response = client.newCall(request).execute()
                //得到返回值
                val responseData = response.body?.string()
                if(responseData != null){
                    runOnUiThread(){
                        binding.tvResponse.text = responseData
                    }
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}