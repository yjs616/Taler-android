package com.example.taler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityLookRoundBinding

class LookRoundActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityLookRoundBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLookRoundBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}