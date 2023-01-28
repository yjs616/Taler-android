package com.example.taler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityMusicBinding

class MusicActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMusicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}