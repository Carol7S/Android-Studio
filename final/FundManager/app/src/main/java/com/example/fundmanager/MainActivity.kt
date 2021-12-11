package com.example.fundmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener(){
            val intent = Intent(this,LoginActivity::class.java)
            startActivityForResult(intent,1)
        }

        binding.btnRegister.setOnClickListener(){
            val intent = Intent(this,RegisterActivity::class.java)
            startActivityForResult(intent,1)
        }

        binding.btnFund.setOnClickListener(){
            val intent = Intent(this,FundManagerActivity::class.java)
            startActivityForResult(intent,1)
        }
    }
}