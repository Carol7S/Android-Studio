package com.example.test1

import android.content.Context
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
import com.example.test1.data.TrainSearchHistory
import com.example.test1.databinding.ActivityTrainSearchBinding
import java.io.*

class TrainSearchActivity : AppCompatActivity() {
    private val history_data = ArrayList<TrainSearchHistory>()
    private val file_name = "history"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train_search)
        val binding = ActivityTrainSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //初始化数据
        initData()
        //布局管理器
        val layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        binding.tvHistory.layoutManager = layoutManager
        val adapter = TrainAdapter(history_data)
        binding.tvHistory.adapter = adapter

        // itemClick
        adapter.setOnKotlinItemClickListener(object : TrainAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                //Toast.makeText(applicationContext, position, Toast.LENGTH_SHORT).show()
                binding.startStation.setText(history_data[position].startStation)
                binding.endStation.setText(history_data[position].endStation)
            }
        })

        //查询按钮
        binding.btnTrainSearch.setOnClickListener{
            val startStation = binding.startStation.text.toString()
            val endStation = binding.endStation.text.toString()
            history_data.add(TrainSearchHistory(startStation,endStation))
            //load(binding)
            //refreshHistory(binding)
            //写入文件
            save(binding)
        }

        //清空历史按钮
        binding.tvClear.setOnClickListener{
            history_data.clear()
            refreshHistory(binding)
        }

        //显示最后一次的结果
        if(history_data.size != 0) {
        binding.startStation.setText(history_data[0].startStation)
        binding.endStation.setText(history_data[0].endStation)
        }
    }

    fun initData(){
        val builder = java.lang.StringBuilder()
        try{
            val input = openFileInput(file_name)
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    builder.append(it).append(", ")
                    val info = parseHistory(it)
                    history_data.add(info)
                }
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    fun load(binding: ActivityTrainSearchBinding){
        val builder = java.lang.StringBuilder()
        try{
            val input = openFileInput(file_name)
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    builder.append(it).append(", ")
                    val info = parseHistory(it)
                    history_data.add(info)
                }
            }
            //binding.tvHistory.setText(builder.toString())
//            val adapter = TrainAdapter(history_data)
//            binding.tvHistory.adapter = adapter
            refreshHistory(binding)
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    private fun parseHistory(history_str:String):TrainSearchHistory{
        val splitIndex = history_str.indexOf(",")
        val startStation = history_str.substring(0,splitIndex).trim()
        val endStation = history_str.substring(splitIndex+1,history_str.lastIndex).trim()
        return TrainSearchHistory(startStation,endStation)
    }

    fun save(binding: ActivityTrainSearchBinding){
        try {
            //打开文件
            val output = openFileOutput(file_name, Context.MODE_APPEND)
            // 缓冲流
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                for(item in history_data){
                    writer.write("${item.startStation},${item.endStation}  ")//csv格式
                    writer.newLine()
                }
            }
            Toast.makeText(this, "已写入", Toast.LENGTH_LONG).show()
            refreshHistory(binding)
        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    fun refreshHistory(binding:ActivityTrainSearchBinding){
//        val builder = StringBuilder()
//        for(item in history_data){
//            builder.append("${item.startStation}--${item.endStation}  ")
//        }
        val adapter = TrainAdapter(history_data)
        binding.tvHistory.adapter = adapter
    }

    //界面控件的容器
    private class TrainViewHolder(view1 : View) : RecyclerView.ViewHolder(view1){
        //获取对应的控件
        val tvStartStation : TextView = view1.findViewById(R.id.tvStartStation)
        val tvEndStation : TextView = view1.findViewById(R.id.tvEndStation)
    }

    //适配器
    private class TrainAdapter(val trainList: List<TrainSearchHistory>): RecyclerView.Adapter<TrainViewHolder>() {
        private var itemClickListener: IKotlinItemClickListener? = null

        //创建视图
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.train_history, parent, false)
            return TrainViewHolder(view)
        }

        //绑定数据
        override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
            val info = trainList[position]
            holder.tvStartStation.text = info.startStation
            holder.tvEndStation.text = info.endStation
            holder.itemView.setOnClickListener {
                itemClickListener!!.onItemClickListener(position)
            }
        }

        //列表的行数
        override fun getItemCount() = trainList.size

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
}