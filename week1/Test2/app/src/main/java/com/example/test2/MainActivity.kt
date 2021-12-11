package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTest.text = "Test1"
        binding.btnTest.setOnClickListener(){
            binding.btnTest.setText("Test_Click!")
            //TV
            binding.tvTest.setText("这是一个测试！")

        }

        binding.btnLog.setOnClickListener(){

            val text = binding.edTest.text.toString()
            binding.tvTest.setText(text)
            //MessageBox
            Toast.makeText(this,text, Toast.LENGTH_LONG).show()

        }


    }

}



