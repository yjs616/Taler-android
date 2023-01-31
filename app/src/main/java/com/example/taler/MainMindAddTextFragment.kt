package com.example.taler

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.taler.databinding.DialogInputBinding
import com.example.taler.databinding.FragmentMainMindAddTextBinding
import com.example.taler.databinding.FragmentMainMindCircleBinding
import com.example.taler.databinding.FragmentMainWordAddTextBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainMindAddTextFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMindAddTextFragment : Fragment() {
    //전역변수 선언
    private var text:String=""

    private lateinit var viewBinding: FragmentMainMindAddTextBinding
    private lateinit var mindCircleBinding: FragmentMainMindCircleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainMindAddTextBinding.inflate(layoutInflater)
        val dialogInputBinding= DialogInputBinding.inflate(inflater, container, false)

        return viewBinding.root


        /*
        viewBinding.btnComplete.setOnClickListener(View.OnClickListener {
            //viewBinding.twoCircle.setBackgroundResource(R.color.black)
            //editTxt 값 담기
            text = viewBinding.edtText.text.toString()
            //memo 부분 초기화 - 비우기
            viewBinding.edtText.setText(" ")

        })*/

    }
}