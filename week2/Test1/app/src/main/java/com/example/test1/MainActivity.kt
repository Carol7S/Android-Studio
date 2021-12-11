package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.test1.databinding.ActivityMainBinding

fun calc(num1:Double):Double{
    var tax:Double = 0.0
    var _tax:Double = 0.0
    if(num1 < 4000){
        tax = (num1 - 800) * 0.2
    }
    else{
        _tax = num1 * 0.8
        if(_tax < 20000){
            tax = _tax * 0.2
        }
        else if (20000 < _tax && _tax < 50000){
            tax = _tax * 0.3 - 2000
        }
        else{
            tax = _tax * 0.4 - 7000
        }
    }
    return tax
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtMoney.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                //TODO("Not yet implemented")
                var str = binding.txtMoney.text.toString()
                // str -> num -> 计算 -> str
                var num = str.toDouble()
                var tax = calc(num)
                str = tax.toString()
                binding.tvTax.setText(str)
            }

        })
    }
}