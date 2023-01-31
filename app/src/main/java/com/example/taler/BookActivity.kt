package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnModify.setOnClickListener {
            val intent = Intent(this,CharacterCustomActivity::class.java)
            startActivity(intent)
        }
    }
}