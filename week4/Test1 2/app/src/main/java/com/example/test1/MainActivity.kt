package com.example.test1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var trainList:ArrayList<TrainInfo> = ArrayList<TrainInfo>()
    private var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //初始化数据
        initData()
        //布局管理器
        val layoutManager = LinearLayoutManager(this)
        binding.recycleviewTrain.layoutManager = layoutManager
        val adapter = TrainAdapter(trainList)
        binding.recycleviewTrain.adapter = adapter


        // itemClick
        adapter.setOnKotlinItemClickListener(object : TrainAdapter.IKotlinItemClickListener {
            override fun onItemClickListener(position: Int) {
                //Toast.makeText(applicationContext, trainList[position].name, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity,MainActivity2::class.java)
                intent.putExtra("shangwu_num", trainList[position].shangWu)
                intent.putExtra("first_num", trainList[position].firstNumber)
                intent.putExtra("second_num", trainList[position].secondNumber)
                intent.putExtra("wuzuo_num", trainList[position].wuZuo)
                pos = position
                startActivityForResult(intent,1)
            }
        })
//        binding.btnTest.setOnClickListener(){
//            initData()
//            val builder = StringBuilder()
//            for(info in trainList){
//                builder.append(info.toString()).append("\n")
//            }
//            Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show()
//        }

    }
    //界面控件的容器
    private class TrainViewHolder(view1 : View) : RecyclerView.ViewHolder(view1){
        //获取对应的控件
        val tvName : TextView = view1.findViewById(R.id.tvName)
        val tvStartStation : TextView = view1.findViewById(R.id.tvStartStation)
        val tvEndStation : TextView = view1.findViewById(R.id.tvEndStation)
        val tvStartTime : TextView = view1.findViewById(R.id.tvStartTime)
        val tvEndTime : TextView = view1.findViewById(R.id.tvEndTime)
        val tvShangWu : TextView = view1.findViewById(R.id.tvShangWu)
        val tvFirstNumber : TextView = view1.findViewById(R.id.tvFirstNumber)
        val tvSecondNumber : TextView = view1.findViewById(R.id.tvSecondNumber)
        val tvWuZuo : TextView = view1.findViewById(R.id.tvWuZuo)
    }

    //适配器
    private class TrainAdapter(val trainList: List<TrainInfo>): RecyclerView.Adapter<TrainViewHolder>() {
        private var itemClickListener: IKotlinItemClickListener? = null

        //创建视图
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.train_elem_layout, parent, false)
            return TrainViewHolder(view)
        }

        //绑定数据
        override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
            val info = trainList[position]
            holder.tvName.text = info.name
            holder.tvStartStation.text = info.startStation
            holder.tvEndStation.text = info.endStation
            holder.tvStartTime.text = info.startTime
            holder.tvEndTime.text = info.endTime
            holder.tvShangWu.text = "商务:${info.shangWu}"
            holder.tvFirstNumber.text = "一等:${info.firstNumber}张"
            holder.tvSecondNumber.text = "二等:${info.secondNumber}张"
            holder.tvWuZuo.text = "无座:${info.wuZuo}"

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

    private fun initData(){
        trainList.add(TrainInfo("G2312", "杭州", "衢州", "6:30", "7:48",20,50,100,5))
        trainList.add(TrainInfo("G2314", "金华", "宁波", "6:30", "7:48",30,60,110,3))
        trainList.add(TrainInfo("G2315", "义乌", "绍兴", "6:30", "7:48",25,55,105,29))
        trainList.add(TrainInfo("G2311", "衢州", "湖州", "6:30", "7:48",15,45,90,230))
        trainList.add(TrainInfo("G2320", "江山", "南京", "6:30", "7:48",10,40,80,65))
    }

    //回调
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> if (resultCode == Activity.RESULT_OK){
                val shangwu_num = data?.getIntExtra("shangwu_num", 500)
                val first_num = data?.getIntExtra("first_num", 0)
                val second_num = data?.getIntExtra("second_num", 0)
                val wuzuo_num = data?.getIntExtra("wuzuo_num", 0)
                trainList[pos].shangWu = shangwu_num!!
                trainList[pos].firstNumber = first_num!!
                trainList[pos].secondNumber = second_num!!
                trainList[pos].wuZuo = wuzuo_num!!
//                Toast.makeText(this, "Hello!!!", Toast.LENGTH_LONG).show()
//                Toast.makeText(this, "${pos}", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "${trainList[pos].wuZuo}", Toast.LENGTH_LONG).show()
                val layoutManager = LinearLayoutManager(this)
                binding.recycleviewTrain.layoutManager = layoutManager
                val adapter = TrainAdapter(trainList)
                binding.recycleviewTrain.adapter = adapter


                // itemClick
                adapter.setOnKotlinItemClickListener(object : TrainAdapter.IKotlinItemClickListener {
                    override fun onItemClickListener(position: Int) {
                        //Toast.makeText(applicationContext, trainList[position].name, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity,MainActivity2::class.java)
                        intent.putExtra("shangwu_num", trainList[position].shangWu)
                        intent.putExtra("first_num", trainList[position].firstNumber)
                        intent.putExtra("second_num", trainList[position].secondNumber)
                        intent.putExtra("wuzuo_num", trainList[position].wuZuo)
                        pos = position
                        startActivityForResult(intent,1)
                    }
                })
            }
        }
    }
}