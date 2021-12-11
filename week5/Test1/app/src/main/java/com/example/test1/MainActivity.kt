package com.example.test1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test1.databinding.ActivityMainBinding
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private val file_name = "data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnFileWrite.setOnClickListener{
            save(binding)
        }

        binding.btnFileRead.setOnClickListener{
            read(binding)
        }

        binding.btnTrainSearch.setOnClickListener{
            val intent = Intent(this, TrainSearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun save(binding: ActivityMainBinding){
        //val binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        val data = binding.txtContent.text.toString()
        try {
            //打开文件
            val output = openFileOutput(file_name, Context.MODE_APPEND)
//            //写入文件 二进制流
//            output.write(data.toByteArray())
            // 字符流
            val outputStreamWriter = OutputStreamWriter(output)
            val num = 100
//            outputStreamWriter.use {
//                it.write("数据：${data} -- ${num}")
//            }
            // 缓冲流
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write("数据：${data} -- ${num}")
                it.newLine()
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
        Toast.makeText(this, "写入完毕", Toast.LENGTH_LONG).show()
    }

    private fun read(binding: ActivityMainBinding){
        val builder = StringBuilder()
        try{
            val input = openFileInput(file_name)
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    builder.append(it).append(", ")
                }
            }
            binding.txtContent.setText(builder.toString())
        }catch (e:IOException){
            e.printStackTrace()
        }
    }
}