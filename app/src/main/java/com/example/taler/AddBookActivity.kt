package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityAddBookBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AddBookActivity : AppCompatActivity() {
    // 프래그먼트 트렌젝션 설정 안됨
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

        val bottomNav = viewBinding.bottomNav as BottomNavigationView
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
                        .addToBackStack(null)
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
    }


}