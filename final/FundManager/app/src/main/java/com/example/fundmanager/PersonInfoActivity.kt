package com.example.fundmanager

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.fundmanager.databinding.ActivityFundAddBinding
import com.example.fundmanager.databinding.ActivityPersonInfoBinding
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder
import kotlin.concurrent.thread

class PersonInfoActivity : AppCompatActivity() {
    private val server_ip = "192.168.1.100"
    private val update_person_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/test.jsp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_info)
        val binding = ActivityPersonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 用户昵称
        var nickName = ""
        // 个性签名
        var signature = ""
        // 余额
        var money = ""

        binding.tvNickNameE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nickName = binding.tvNickNameE.text.toString()
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nickName = binding.tvNickNameE.text.toString()
            }
            override fun afterTextChanged(p0: Editable?) {
                nickName = binding.tvNickNameE.text.toString()
            }
        })

        binding.tvSignatureE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                signature = binding.tvSignatureE.text.toString()
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                signature = binding.tvSignatureE.text.toString()
            }
            override fun afterTextChanged(p0: Editable?) {
                signature = binding.tvSignatureE.text.toString()
            }
        })

        binding.tvMoneyE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                money = binding.tvMoneyE.text.toString()
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                money = binding.tvMoneyE.text.toString()
            }
            override fun afterTextChanged(p0: Editable?) {
                money = binding.tvMoneyE.text.toString()
            }
        })



        binding.btnReturnPara.setOnClickListener() {
            addFundByOkHttp(
                update_person_url,
                nickName,
                signature,
                money
            )
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }//构造函数结束




    private fun addFundByOkHttp(url:String, nickName:String, signature:String, money:String){
        thread {
            try {
                val client = OkHttpClient()
                //构建数据
                val requestBody = FormBody.Builder()
                    .add("name", URLEncoder.encode(nickName,"utf-8"))
                    .add("value", URLEncoder.encode(signature,"utf-8"))
                    .add("size", money)
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
//                if(responseData != null){
//                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}