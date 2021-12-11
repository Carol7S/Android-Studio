package com.example.fundmanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fundmanager.data.FundInfo
import com.example.fundmanager.databinding.ActivityFundManagerBinding
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.net.URLEncoder
import kotlin.concurrent.thread

class FundManagerActivity : AppCompatActivity() {
    private var FundList:ArrayList<FundInfo> = ArrayList<FundInfo>()
    private val fundArray:ArrayList<FundInfo> = ArrayList<FundInfo>()//从服务端获取数据
    private var pos = 0
    private val server_ip = "192.168.1.101"
    private val add_fund_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/add_fund.jsp"
    private val get_all_fund_json_url = "http://${server_ip}:8080/AndroidServer_Web_exploded/get_all_fund_json.jsp"

    var flag_get_fund_json_end:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_manager)
        val binding = ActivityFundManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //获取基金数据
        getAllFundByJson(binding, get_all_fund_json_url)
        while (flag_get_fund_json_end == false){
            continue
        }
        flag_get_fund_json_end = false
        //初始化数据
        initData()
        //布局管理器
        val layoutManager = LinearLayoutManager(this)
        binding.recycleviewFund.layoutManager = layoutManager
        val adapter = FundAdapter(FundList)
        binding.recycleviewFund.adapter = adapter


        // itemClick
        adapter.setOnKotlinItemClickListener(object : FundAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                //Toast.makeText(applicationContext, trainList[position].name, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@FundManagerActivity,FundInnerActivity::class.java)
                intent.putExtra("holdNum", FundList[position].holdNum)
                pos = position
                startActivityForResult(intent,1)
            }
        })


        //获取json fund
        binding.btnGetFund.setOnClickListener(){
            getAllFundByJson(binding, get_all_fund_json_url)
            while (flag_get_fund_json_end == false){
                continue
            }
//            println(fundArray)
            fundArray.clear()
        }


    } //构造函数结束



    //数据初始化
    private fun initData(){
//        FundList.add(FundInfo("中欧盛世成长混合","3.0316","5.87亿","9年237天","2.29%","3.46%","18.35%","14.41%",200))
//        FundList.add(FundInfo("金信民长混合A","2.3682","1.00亿","1年184天","0.09%","3.50%","18.30%","104.65%",100))
//        FundList.add(FundInfo("中欧永裕混合A","2.1550","5.11亿","6年170天","2.28%","3.44%","18.27%","13.50%",300))
//        FundList.add(fundArray[0])

        for (i in 0 until fundArray.size){
            FundList.add(fundArray[i])
        }
//        println(FundList)
        fundArray.clear()
    }



    //界面控件的容器
    private class FundViewHolder(view1 : View) : RecyclerView.ViewHolder(view1){
        //获取对应的控件
        val tvName : TextView = view1.findViewById(R.id.tvName)
        val tvValue : TextView = view1.findViewById(R.id.tvValue)
        val tvSize : TextView = view1.findViewById(R.id.tvSize)
        val tvTime : TextView = view1.findViewById(R.id.tvTime)
        val tvDailyIncrease : TextView = view1.findViewById(R.id.tvDailyIncrease)
        val tvWeeklyIncrease : TextView = view1.findViewById(R.id.tvWeeklyIncrease)
        val tvMonthlyIncrease : TextView = view1.findViewById(R.id.tvMonthlyIncrease)
        val tvYearlyIncrease : TextView = view1.findViewById(R.id.tvYearlyIncrease)
        val tvHoldNum : TextView = view1.findViewById(R.id.tvHoldNum)
    }



    //适配器
    private class FundAdapter(val fundLIst: List<FundInfo>): RecyclerView.Adapter<FundViewHolder>() {
        private var itemClickListener: IKotlinItemClickListener? = null

        //创建视图
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fund_elem_layout, parent, false)
            return FundViewHolder(view)
        }

        //绑定数据
        override fun onBindViewHolder(holder: FundViewHolder, position: Int) {
            val info = fundLIst[position]
            holder.tvName.text = info.name
            holder.tvValue.text = "净值：${info.value}"
            holder.tvSize.text = "规模：${info.size}"
            holder.tvTime.text = "周期：${info.time}"
            holder.tvDailyIncrease.text = "日增长:${info.DailyIncrease}"
            holder.tvWeeklyIncrease.text = "周增长:${info.WeeklyIncrease}"
            holder.tvMonthlyIncrease.text = "月增长:${info.MonthlyIncrease}"
            holder.tvYearlyIncrease.text = "年增长:${info.YearlyIncrease}"
            holder.tvHoldNum.text = "持有量:${info.holdNum}"

            holder.itemView.setOnClickListener {
                itemClickListener!!.onItemClickListener(position)
            }
        }

        //列表的行数
        override fun getItemCount() = fundLIst.size

        // 点击事件

        // 提供set方法
        fun setOnKotlinItemClickListener(itemClickListener: IKotlinItemClickListener) {
            this.itemClickListener = itemClickListener
        }

        //自定义接口
        interface IKotlinItemClickListener {
            fun onItemClickListener(position: Int)
        }
    }



    //回调
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val binding = ActivityFundManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                val holdNum = data?.getIntExtra("holdNum", 0)
                FundList[pos].holdNum = holdNum!!
//                Toast.makeText(this, "Hello!!!", Toast.LENGTH_LONG).show()
//                Toast.makeText(this, "${pos}", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "${FundList[pos].holdNum}", Toast.LENGTH_LONG).show()
                val layoutManager = LinearLayoutManager(this)
                binding.recycleviewFund.layoutManager = layoutManager
                val adapter = FundAdapter(FundList)
                binding.recycleviewFund.adapter = adapter


                // itemClick
                adapter.setOnKotlinItemClickListener(object : FundAdapter.IKotlinItemClickListener {
                    override fun onItemClickListener(position: Int) {
                        //Toast.makeText(applicationContext, FundList[position].name, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@FundManagerActivity, FundInnerActivity::class.java)
                        intent.putExtra("holdNum", FundList[position].holdNum)
                        pos = position
                        startActivityForResult(intent, 1)
                    }
                })
            }
        }
    }


    // 通过json得到基金数据
    private fun getAllFundByJson(binding: ActivityFundManagerBinding, url:String){
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
                if(responseData != null){
//                    globalResponse = result.toString()
//                    println(responseData)
                }
                parseJson(responseData)
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
                val name = jsonObject.getString("name")
                val value = jsonObject.getString("value")
                val size = jsonObject.getString("size")
                val time = jsonObject.getString("time")

                val DailyIncrease = jsonObject.getString("DailyIncrease")
                val WeeklyIncrease = jsonObject.getString("WeeklyIncrease")
                val MonthlyIncrease = jsonObject.getString("MonthlyIncrease")
                val YearlyIncrease = jsonObject.getString("YearlyIncrease")

                val holdNum = jsonObject.getInt("holdNum")
//                builder.append("$id--$name--$value--$size--$time--$DailyIncrease--$WeeklyIncrease--$MonthlyIncrease--$YearlyIncrease--$holdNum")
                fundArray.add(FundInfo(name, value, size, time, DailyIncrease, WeeklyIncrease, MonthlyIncrease, YearlyIncrease, holdNum))
            }
//            println(fundArray)
            flag_get_fund_json_end = true
        }
        catch (e:Exception){
            e.printStackTrace()
        }
//        return builder.toString()
    }
}