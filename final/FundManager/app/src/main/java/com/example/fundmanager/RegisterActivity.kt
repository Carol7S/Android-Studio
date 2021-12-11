package com.example.fundmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fundmanager.databinding.ActivityLoginBinding
import com.example.fundmanager.databinding.ActivityRegisterBinding
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity() {
    val server_ip = "192.168.1.101"
    val add_user_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/add_user.jsp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
        setContentView(R.layout.activity_register)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener(){
            val username = binding.username.text.toString()
            var password = ""
            val password1 = binding.password.text.toString()
            val password2 = binding.password2.text.toString()
            val permission = "user"

            if(password1 == password2){
                password = password1
            }else{
                Toast.makeText(this,"请输入相同的密码",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if ((username!=null&&username.length>0) && (password!=null&&password.length>0)){
                addUserByOkHttp(binding,add_user_url,username,password,permission)
            }
        }
    }

    private fun addUserByOkHttp(binding: ActivityRegisterBinding,url:String,username:String,password:String,permission:String){
        thread {
            try {
                val client = OkHttpClient()
                //构建数据
                val requestBody = FormBody.Builder()
                    .add("username", URLEncoder.encode(username,"utf-8"))
                    .add("password",URLEncoder.encode(password,"utf-8"))
                    .add("permission",URLEncoder.encode(permission,"utf-8"))
                    .build()
                //构建请求

                val request = Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()

                //执行
                val response = client.newCall(request).execute()
                //得到返回值
                val responseData = response.body?.string()
                if(responseData != null){
                    runOnUiThread(){
                    }
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}