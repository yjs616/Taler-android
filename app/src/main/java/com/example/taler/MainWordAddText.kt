package com.example.taler

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taler.databinding.FragmentMainWordAddTextBinding


class MainWordAddText : Fragment() {

    //전역변수 선언
    private var text:String=""

    private lateinit var viewBinding: FragmentMainWordAddTextBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding =FragmentMainWordAddTextBinding.inflate(layoutInflater)
        return viewBinding.root
        viewBinding.imgCheck.setOnClickListener(View.OnClickListener {
            //viewBinding.twoCircle.setBackgroundResource(R.color.black)
            //editTxt 값 담기
            text = viewBinding.edtText.text.toString()
            //memo 부분 초기화 - 비우기
            viewBinding.edtText.setText(" ")

        })

    }



}