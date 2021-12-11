package com.example.fundmanager

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.fundmanager.databinding.ActivityFundAddBinding
import com.example.fundmanager.databinding.ActivityFundDeleteBinding
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder
import kotlin.concurrent.thread

class FundDeleteActivity : AppCompatActivity() {
    private val server_ip = "192.168.1.100"
    private val delete_fund_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/delete_fund.jsp"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_delete)
        val binding = ActivityFundDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 基金名称
        var name = ""
        binding.tvNameE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                name = binding.tvNameE.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                name = binding.tvNameE.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                name = binding.tvNameE.text.toString()
            }
        })

        binding.btnReturnPara.setOnClickListener() {
            addFundByOkHttp(
                delete_fund_url,
                name,
            )
//            setResult(Activity.RESULT_OK,intent)
            finish()
        }



    }//构造函数结束




    private fun addFundByOkHttp(url:String, name:String){
        thread {
            try {
                val client = OkHttpClient()
                //构建数据
                val requestBody = FormBody.Builder()
                    .add("name", URLEncoder.encode(name,"utf-8"))
                    .build()
                //构建请求

                val request = Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()

                //执行
                val response = client.newCall(request).execute()
                //得到返回值
//                val responseData = response.body?.string()
//                if(responseData != null){
//                }
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}