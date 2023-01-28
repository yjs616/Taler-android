package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EmailNumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_num)


        val moveBtn4: Button = findViewById(R.id.btn48)

        moveBtn4.setOnClickListener {
            val intent: Intent = Intent(this,PrivacyPolicyActivity::class.java)
            startActivity(intent)
        }
    }
}