package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MomLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mom_login)


        val moveBtn3: Button = findViewById(R.id.btn18)

        moveBtn3.setOnClickListener {
            val intent: Intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        val moveBtn4: Button = findViewById(R.id.btn19)

        moveBtn4.setOnClickListener {
            val intent: Intent = Intent(this,MomLoginSettingActivity::class.java)
            startActivity(intent)
        }
    }
}