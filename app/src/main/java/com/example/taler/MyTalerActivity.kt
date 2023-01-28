package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityMyTalerBinding

class MyTalerActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMyTalerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMyTalerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //둘러보기 버튼
        viewBinding.btnLook.setOnClickListener {
            val intent = Intent(this,LookRoundActivity::class.java )
            startActivity(intent)
        }

        //책 추가 버튼
        viewBinding.imgAdd.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }

        //책 버튼 [네모]
        /*viewBinding.book.setOnClickListener{
            val intent = Intent(this, BookActivity::class.java)
            startActivity(intent)
        }*/

        //친구꺼 보기 버튼 [원]
        /*viewBinding.friend.setOnClickListener{
            val intent = Intent(this,FriendTalerActivity::class.java)
            startActivity(intent)
        }*/

    }
}