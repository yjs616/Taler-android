package com.example.taler

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartLoginActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_login)
        val moveBtn: Button = findViewById<Button>(R.id.btn16)

        moveBtn.setOnClickListener {

            val intent: Intent = Intent(this, BabyLoginActivity::class.java)
            startActivity(intent)
        }

        val moveBtn2: Button = findViewById(R.id.btn17)

        moveBtn2.setOnClickListener {
            val intent: Intent = Intent(this, MomLoginActivity::class.java)
            startActivity(intent)
        }
    }
}