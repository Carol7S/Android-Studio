package com.example.test1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test1.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stu_id = intent.getStringExtra("stu_id")
        binding.tvTest.text = stu_id

        binding.btnReturnPara.setOnClickListener(){
            val intent=Intent()
            intent.putExtra("stu_name","王五")
            setResult(Activity.RESULT_CANCELED,intent)
            finish()
        }

    }
}