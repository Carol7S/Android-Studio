package com.example.fundmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getString
import com.example.fundmanager.data.FundInfo
import com.example.fundmanager.data.UserInfo
import com.example.fundmanager.databinding.ActivityLoginBinding
import com.example.fundmanager.databinding.ActivityMainBinding
import com.example.fundmanager.databinding.ActivityRegisterBinding
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.lang.StringBuilder
import java.net.URLEncoder
import java.security.MessageDigest
import kotlin.concurrent.thread




class LoginActivity : AppCompatActivity() {
    var server_ip:String = "192.168.1.101"
//    val get_all_user_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/get_all_user.jsp"
    val get_all_user_json_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/get_all_user_json.jsp"
    private val UserArray:ArrayList<UserInfo> = ArrayList<UserInfo>()//从服务端获取数据
    var flag_get_user_json_end:Boolean = false


    //构造函数
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //资源不能在onCreate之前使用
//        server_ip = getString(R.string.server_ip)
//        println(server_ip)
        //登录按钮
        binding.btnLogin.setOnClickListener(){
            getAllUserByJson(binding,get_all_user_json_url)
            while (flag_get_user_json_end == false){
                continue
            }
            flag_get_user_json_end = false
            //println(nameArray)
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            for(i in 0 until UserArray.size){
                if(UserArray[i].username == username){
                    if(md5(password) == UserArray[i].password) {
                        if(UserArray[i].permission == "user") {
                            val intent = Intent(this, FundManagerActivity::class.java)
                            startActivityForResult(intent, 1)
                        }
                        else{
                            val intent = Intent(this, FundManagerAdminActivity::class.java)
                            startActivityForResult(intent, 1)
                        }
                    }
                }
            }
            UserArray.clear()
        }


        //注册按钮
        binding.btnRegister.setOnClickListener(){
            val intent = Intent(this,RegisterActivity::class.java)
            startActivityForResult(intent,1)
        }



    }// 构造函数结束



    //获取用户，进行判断
    private fun getAllUserByOkHttp(binding: ActivityLoginBinding, url:String){
        thread {
            try {
                val client = OkHttpClient()
                //构建请求

                val request = Request.Builder()
                    .url(url)
                    .build()

                //执行
                val response = client.newCall(request).execute()
                //得到返回值
                val responseData = response.body?.string()
                if(responseData != null){
                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun getAllUserByJson(binding: ActivityLoginBinding, url:String){
        thread {
            try {
                val client = OkHttpClient()
                //构建请求

                val request = Request.Builder()
                    .url(url)
                    .build()

                //执行
                val response = client.newCall(request).execute()
                //得到返回值
                val responseData = response.body!!.string()
                val result = parseJson(responseData)
//                if(responseData != null){
////                    globalResponse = result
//                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun parseJson(jsonStr:String){
//        val builder = StringBuilder()
        try {
            //json数组
            val jsonArray = JSONArray(jsonStr)
            for(i in 0 until jsonArray.length()){
                //依次取出元素
                val jsonObject = jsonArray.getJSONObject(i)
                val id = jsonObject.getInt("id")
                val username = jsonObject.getString("username")
                val password = jsonObject.getString("password")
                val permission = jsonObject.getString("permission")
//                builder.append("$id--$username--$password--$permission")
//                nameArray.add(username.toString())
//                passArray.add(password.toString())
//                permissionArray.add(permission.toString())
                UserArray.add(UserInfo(username,password,permission))
            }
            flag_get_user_json_end = true
        }
        catch (e:Exception){
            e.printStackTrace()
        }
//        return builder.toString()
    }


    /** md5加密 */
    fun md5(content: String): String {
        val hash = MessageDigest.getInstance("MD5").digest(content.toByteArray())
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            var str = Integer.toHexString(b.toInt())
            if (b < 0x10) {
                str = "0$str"
            }
            hex.append(str.substring(str.length -2))
        }
        return hex.toString()
    }

}