package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicyActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPrivacyPolicyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnAgree.setOnClickListener {
            startActivity(Intent(this,SelectBookActivity::class.java))
        }
    }
}