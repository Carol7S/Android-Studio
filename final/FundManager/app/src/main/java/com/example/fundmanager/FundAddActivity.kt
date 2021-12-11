package com.example.fundmanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.fundmanager.databinding.ActivityFundAddBinding
import com.example.fundmanager.databinding.ActivityFundInnerBinding
import com.example.fundmanager.databinding.ActivityFundManagerAdminBinding
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLEncoder
import kotlin.concurrent.thread

class FundAddActivity : AppCompatActivity() {
    private val server_ip = "192.168.1.100"
    private val add_fund_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/add_fund.jsp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_add)
        val binding = ActivityFundAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 基金名称
        var name = ""
        var value = ""
        var size = ""
        var time = ""
        var DailyIncrease = ""
        var WeeklyIncrease = ""
        var MonthlyIncrease = ""
        var YearlyIncrease = ""
        var holdNum = ""


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
        binding.tvValueE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                value = binding.tvValueE.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                value = binding.tvValueE.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                value = binding.tvValueE.text.toString()
            }
        })
        binding.tvSizeE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                size = binding.tvSizeE.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                size = binding.tvSizeE.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                size = binding.tvSizeE.text.toString()
            }
        })
        binding.tvTimeE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                time = binding.tvTimeE.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                time = binding.tvTimeE.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                time = binding.tvTimeE.text.toString()
            }
        })
        //增长率
        binding.tvDailyIncreaseE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                DailyIncrease = binding.tvDailyIncreaseE.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                DailyIncrease = binding.tvDailyIncreaseE.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                DailyIncrease = binding.tvDailyIncreaseE.text.toString()
            }
        })
        binding.tvWeeklyIncreaseE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                WeeklyIncrease = binding.tvWeeklyIncreaseE.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                WeeklyIncrease = binding.tvWeeklyIncreaseE.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                WeeklyIncrease = binding.tvWeeklyIncreaseE.text.toString()
            }
        })
        binding.tvMonthlyIncreaseE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                MonthlyIncrease = binding.tvMonthlyIncreaseE.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                MonthlyIncrease = binding.tvMonthlyIncreaseE.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                MonthlyIncrease = binding.tvMonthlyIncreaseE.text.toString()
            }
        })
        binding.tvYearlyIncreaseE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                YearlyIncrease = binding.tvYearlyIncreaseE.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                YearlyIncrease = binding.tvYearlyIncreaseE.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                YearlyIncrease = binding.tvYearlyIncreaseE.text.toString()
            }
        })

        binding.tvHoldNumE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                holdNum = binding.tvHoldNumE.text.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                holdNum = binding.tvHoldNumE.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                holdNum = binding.tvHoldNumE.text.toString()
            }
        })




        binding.btnReturnPara.setOnClickListener() {
            addFundByOkHttp(
                binding,
                add_fund_url,
                name,
                value,
                size,
                time,
                DailyIncrease,
                WeeklyIncrease,
                MonthlyIncrease,
                YearlyIncrease,
                holdNum
            )
//            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }//构造函数结束




    private fun addFundByOkHttp(binding: ActivityFundAddBinding, url:String, name:String, value:String, size:String, time:String, DailyIncrease:String, WeeklyIncrease:String, MonthlyIncrease:String, YearlyIncrease:String, holdNum:String){
        thread {
            try {
                val client = OkHttpClient()
                //构建数据
                val requestBody = FormBody.Builder()
                    .add("name", URLEncoder.encode(name,"utf-8"))
                    .add("value", URLEncoder.encode(value,"utf-8"))
                    .add("size", URLEncoder.encode(size,"utf-8"))
                    .add("time", URLEncoder.encode(time,"utf-8"))
                    .add("DailyIncrease", URLEncoder.encode(DailyIncrease,"utf-8"))
                    .add("WeeklyIncrease", URLEncoder.encode(WeeklyIncrease,"utf-8"))
                    .add("MonthlyIncrease", URLEncoder.encode(MonthlyIncrease,"utf-8"))
                    .add("YearlyIncrease", URLEncoder.encode(YearlyIncrease,"utf-8"))
                    .add("holdNum", holdNum)
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