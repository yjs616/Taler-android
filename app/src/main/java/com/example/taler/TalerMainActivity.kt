package com.example.taler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityTalerMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TalerMainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTalerMainBinding

    private lateinit var bottomNav:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTalerMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)



        //bottomNav 연결
        bottomNav = viewBinding.bottomNav as BottomNavigationView
        bottomNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_picture->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(viewBinding.frameFragment.id,MainPictureFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.nav_word->{
                    //이건 word 부분이 fragment일 때
                    supportFragmentManager
                        .beginTransaction()
                        .replace(viewBinding.frameFragment.id,MainWordCircleFragment())
                        .commitAllowingStateLoss()
                    true
                }

                else -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(viewBinding.frameFragment.id,MainMindCircleFragment())
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
    }


}