package com.example.test1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test1.databinding.ActivityMainBinding
import com.example.test1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val start_station = intent.getStringExtra("start_station")
//        val end_station = intent.getStringExtra("end_station")
//        val second_num = intent.getIntExtra("second_num",0)
//        val trainInfo = intent.getSerializableExtra("train")
        val first_num = intent.getIntExtra("first_num",0)
        val second_num = intent.getIntExtra("second_num",0)
        binding.tvTest.text = "一等票：${first_num} --- 二等票：${second_num}"

        binding.btnReturnPara.setOnClickListener(){
            val intent = Intent()
            intent.putExtra("first_num", 50)
            intent.putExtra("train_num","G1213")
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}