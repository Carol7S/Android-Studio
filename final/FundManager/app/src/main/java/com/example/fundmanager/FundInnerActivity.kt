package com.example.fundmanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.fundmanager.databinding.ActivityFundInnerBinding
import com.example.fundmanager.databinding.ActivityFundManagerBinding

class FundInnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_inner)
        val binding = ActivityFundInnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val holdNum = intent.getIntExtra("holdNum", 0)
        binding.tvTest.text = "持有数："
        binding.tvHoldNum.text = "${holdNum}"
        binding.tvHoldNumE.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                val str = binding.tvHoldNumE.text.toString()
                binding.tvHoldNum.setText(str)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
                val str = binding.tvHoldNumE.text.toString()
                binding.tvHoldNum.setText(str)
            }

            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
                val str = binding.tvHoldNumE.text.toString()
                binding.tvHoldNum.setText(str)
            }

        })


        binding.btnReturnPara.setOnClickListener(){
            val intent = Intent()
            intent.putExtra("holdNum", binding.tvHoldNum.text.toString().toInt())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}