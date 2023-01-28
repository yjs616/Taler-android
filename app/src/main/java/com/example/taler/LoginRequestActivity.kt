package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_request)

        val moveBtn4: Button = findViewById(R.id.btn15)

        moveBtn4.setOnClickListener {
            val intent: Intent = Intent(this,MomLoginSettingActivity::class.java)
            startActivity(intent)
        }
    }
}