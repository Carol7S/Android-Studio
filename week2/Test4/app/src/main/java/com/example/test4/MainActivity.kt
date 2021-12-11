package com.example.test4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var i = 2
        binding.btn1.setOnClickListener(){
            if(i == 1){
                binding.img1.setImageResource(R.drawable.test1)
                i++
            }
            else if (i == 2){
                binding.img1.setImageResource(R.drawable.test2)
                i++
            }
            else if (i == 3){
                binding.img1.setImageResource(R.drawable.test3)
                i++
            }
            else if (i == 4){
                binding.img1.setImageResource(R.drawable.test4)
                i++
            }
            else {
                binding.img1.setImageResource(R.drawable.test5)
                i = 1
            }
        }

    }
}