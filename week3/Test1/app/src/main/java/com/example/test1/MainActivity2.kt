package com.example.test1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.example.test1.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val shangwu_num = intent.getIntExtra("shangwu_num", 0)
        val first_num = intent.getIntExtra("first_num", 0)
        val second_num = intent.getIntExtra("second_num", 0)
        val wuzuo_num = intent.getIntExtra("wuzuo_num", 0)
        binding.tvTest.text = "商务票："
        binding.tvShangWu.text = "${shangwu_num}"
        binding.tvShangWuE.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                val str = binding.tvShangWuE.text.toString()
                binding.tvShangWu.setText(str)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                val str = binding.tvShangWuE.text.toString()
                binding.tvShangWu.setText(str)
            }

            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
                val str = binding.tvShangWuE.text.toString()
                binding.tvShangWu.setText(str)
            }

        })
        binding.tvTest2.text = "一等票："
        binding.tvFirstNumber.text = "${first_num}"
        binding.tvFirstNumberE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                val str = binding.tvFirstNumberE.text.toString()
                binding.tvFirstNumber.setText(str)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                val str = binding.tvFirstNumberE.text.toString()
                binding.tvFirstNumber.setText(str)
            }

            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
                val str = binding.tvFirstNumberE.text.toString()
                binding.tvFirstNumber.setText(str)
            }
        })

        binding.tvTest3.text = "二等票："
        binding.tvSecondNumber.text = "${second_num}"
            binding.tvSecondNumberE.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                    val str = binding.tvSecondNumberE.text.toString()
                    binding.tvSecondNumber.setText(str)
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                    val str = binding.tvSecondNumberE.text.toString()
                    binding.tvSecondNumber.setText(str)
                }

                override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
                    val str = binding.tvSecondNumberE.text.toString()
                    binding.tvSecondNumber.setText(str)
                }
            })

        binding.tvTest4.text = "无座票："
        binding.tvWuZuo.text = "${wuzuo_num}"
        binding.tvWuZuoE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                val str = binding.tvWuZuoE.text.toString()
                binding.tvWuZuo.setText(str)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                val str = binding.tvWuZuoE.text.toString()
                binding.tvWuZuo.setText(str)
            }

            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
                val str = binding.tvWuZuoE.text.toString()
                binding.tvWuZuo.setText(str)
            }
        })

        binding.btnReturnPara.setOnClickListener(){
            val intent = Intent()
            intent.putExtra("shangwu_num", binding.tvShangWu.text.toString().toInt())
            intent.putExtra("first_num", binding.tvFirstNumber.text.toString().toInt())
            intent.putExtra("second_num", binding.tvSecondNumber.text.toString().toInt())
            intent.putExtra("wuzuo_num", binding.tvWuZuo.text.toString().toInt())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}