package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val qqList:ArrayList<info> = ArrayList<info>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //初始化数据
        initData()
        //布局管理器
        val layoutManager = LinearLayoutManager(this)
        binding.recycleViewQQ.layoutManager = layoutManager
        val adapter = TrainAdapter(qqList)
        binding.recycleViewQQ.adapter = adapter
    }

    //界面控件的容器
    private class TrainViewHolder(view1 : View) : RecyclerView.ViewHolder(view1){
        //获取对应的控件
        val tvImg : ImageView = view1.findViewById(R.id.tvImg)
        val tvName : TextView = view1.findViewById(R.id.tvName)
        val tvMessage : TextView = view1.findViewById(R.id.tvMessage)
    }

    //适配器
    private class TrainAdapter(val qqList: List<info>): RecyclerView.Adapter<TrainViewHolder>(){
        //创建视图
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.qq_layout,parent,false)
            return TrainViewHolder(view)
        }
        //绑定数据
        override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
            val info = qqList[position]
            holder.tvImg.setImageResource(info.img)
            holder.tvName.text = info.name
            holder.tvMessage.text = info.message
        }
        //列表的行数
        override fun getItemCount() = qqList.size
    }


    fun initData(){
        qqList.add(info(R.drawable.touxiang1,"2015届毕业论文老师", "戴丹:有学生准备出国留学的，要准备..."))
        qqList.add(info(R.drawable.touxiang2,"北纬23", "[图片]我下了这个可视化操作 能把我创..."))
        qqList.add(info(R.drawable.touxiang3,"物联网142公告群", "商至堇:电脑不行"))
    }
}