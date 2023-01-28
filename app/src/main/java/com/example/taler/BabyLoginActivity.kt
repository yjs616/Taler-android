package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BabyLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baby_login)

        val moveBtn4: Button = findViewById(R.id.btn6)

        moveBtn4.setOnClickListener {
            val intent: Intent = Intent(this,BabyLoginSettingActivity::class.java)
            startActivity(intent)
        }
    }
}