package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityMomLoginSettingBinding

class MomLoginSettingActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMomLoginSettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMomLoginSettingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnNext.setOnClickListener {
            startActivity(Intent(this,MomPrivacyPolicyActivity::class.java))
        }
    }
}