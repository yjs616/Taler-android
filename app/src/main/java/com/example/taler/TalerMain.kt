package com.example.taler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityTalerMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class TalerMain : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTalerMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTalerMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    /*private fun removeIcons(view: BottomNavigationView){
        var menuView:BottomNavigationMenuView
        for (i:Int in 0..10 step(1)){

        }
    }*/
}