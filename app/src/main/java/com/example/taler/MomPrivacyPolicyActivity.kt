package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityMomPrivacyPolicyBinding

//부모 약관 동의
class MomPrivacyPolicyActivity : AppCompatActivity() {
    private lateinit var viewBinding :ActivityMomPrivacyPolicyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMomPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnAgree.setOnClickListener {
            startActivity(Intent(this,MyTalerActivity::class.java))
        }
    }
}