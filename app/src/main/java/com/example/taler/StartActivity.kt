package com.example.taler

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityStartBinding

// 임시 시작 페이지. 삭제해도 됩니다!!
class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private lateinit var paint: Paint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectBookBtn.setOnClickListener{
            startActivity(Intent(this,SelectBookActivity::class.java))
        }
        binding.wordFragmentBtn.setOnClickListener{
            startActivity(Intent(this,TalerMainActivity::class.java))
        }
        binding.enrollActivityBtn.setOnClickListener{
            startActivity(Intent(this,EnrollBookActivity::class.java))
        }

    }

}