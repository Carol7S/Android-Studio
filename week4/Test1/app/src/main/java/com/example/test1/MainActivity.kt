package com.example.test1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private val trainList:ArrayList<TrainInfo> = ArrayList<TrainInfo>()

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


//        binding.btnSecondActivity.setOnClickListener(){
//            val intent = Intent(this,SecondActivity::class.java)
//            intent.putExtra("first_num", 50)
//            intent.putExtra("second_num", 100)
////            //intent.putExtra("start_station","杭州")
//////            intent.putExtra("end_station","衢州")
//////            intent.putExtra("second_num",100)
//////            intent.putExtra("train",TrainInfo("杭州","衢州",100))
////            //startActivity(intent)
//            startActivityForResult(intent,1)
//        }

//        binding.btnThirdActivity.setOnClickListener(){
//            //显式调用
//            val intent = Intent(this,ThirdActivity::class.java)
//            intent.putExtra("stu_id","201905010253")
//            //startActivity(intent)
//            startActivityForResult(intent,2)
//            //隐式调用
////            val intent = Intent(Intent.ACTION_VIEW)
////            intent.data = Uri.parse("https://www.baidu.com")
////            startActivity(intent)
////            val intent = Intent(Intent.ACTION_DIAL)
////            intent.data = Uri.parse("tel:10086")
////            startActivity(intent)
//        }

    }

//回调
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode){
//            1 -> if (resultCode == Activity.RESULT_OK){
//                val first_num = data?.getIntExtra("first_num", 0)
//                val train_num = data?.getStringExtra("train_num")
//                binding.tvTest.text = "${first_num} -- ${train_num}"
//                Toast.makeText(this, "${first_num} -- ${train_num}", Toast.LENGTH_LONG).show()
//            }
//            2 -> if(resultCode == Activity.RESULT_CANCELED) {
//                val stu_name = data?.getStringExtra("stu_name")
//                binding.tvTest.text = stu_name
//            }
//        }
//    }
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
    private class TrainAdapter(val trainList: List<TrainInfo>): RecyclerView.Adapter<TrainViewHolder>(){
        //创建视图
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.train_elem_layout,parent,false)
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
        }
        //列表的行数
        override fun getItemCount() = trainList.size

    }

fun initData(){
    trainList.add(TrainInfo("G2312", "杭州", "衢州", "6:30", "7:48",0,1,3,0))
    trainList.add(TrainInfo("G2314", "金华", "宁波", "6:30", "7:48",0,1,0,0))
    trainList.add(TrainInfo("G2315", "义乌", "绍兴", "6:30", "7:48",0,0,0,29))
    trainList.add(TrainInfo("G2311", "衢州", "湖州", "6:30", "7:48",0,0,0,230))
    trainList.add(TrainInfo("G2320", "江山", "南京", "6:30", "7:48",0,3,15,65))
}
}