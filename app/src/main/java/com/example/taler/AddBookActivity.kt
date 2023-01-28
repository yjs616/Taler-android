package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityAddBookBinding

class AddBookActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnMusic.setOnClickListener {
            val intent = Intent(this, MusicActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnBackgroundColor.setOnClickListener {
            val intent = Intent(this,BackgroundColorActivity::class.java)
            startActivity(intent)
        }


        //동화책 등록 -> 이런 동화책은 어떤가요? (enrollActivity)
        viewBinding.btnBookRegister.setOnClickListener {
            val intent = Intent(this,EnrollBookActivity::class.java)
            startActivity(intent)
        }
    }
}