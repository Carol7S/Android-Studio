package com.example.test2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test2.data.userInfo
import com.example.test2.databinding.ActivityMainBinding
import java.io.*

class MainActivity : AppCompatActivity() {
    private val file_name = "data"
    private val userInfo_data = ArrayList<userInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        read(binding)
        binding.btnLogin.setOnClickListener{
            save(binding)
        }
    }

    private fun save(binding: ActivityMainBinding){
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
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
                it.write("${username},${password}")
                it.newLine()
            }
        }catch (e: IOException){
            e.printStackTrace()
        }
        Toast.makeText(this, "写入完毕", Toast.LENGTH_LONG).show()
    }

    private fun read(binding: ActivityMainBinding){
        val builder = StringBuilder()
        var last:Int = 0
        try{
            val input = openFileInput(file_name)
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    builder.append(it).append(", ")
                    val info = parseHistory(it)
                    userInfo_data.add(info)
                }
            }
            last = userInfo_data.size-1
            binding.username.setText(userInfo_data[last].username)
            binding.password.setText(userInfo_data[last].password)
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    private fun parseHistory(history_str:String):userInfo{
        val splitIndex = history_str.indexOf(",")
        val username = history_str.substring(0,splitIndex).trim()
        val password = history_str.substring(splitIndex+1,history_str.lastIndex).trim()
        return userInfo(username,password)
    }
}