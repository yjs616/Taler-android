package com.example.taler

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.ddd.androidutils.DoubleClick
import com.ddd.androidutils.DoubleClickListener
import com.example.taler.databinding.DialogInputBinding
import com.example.taler.databinding.FragmentMainMindCircleBinding
import com.example.taler.databinding.FragmentMainWordCircleBinding


class MainWordCircleFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainWordCircleBinding
    private lateinit var dialogInputBinding:DialogInputBinding
    lateinit var alert:AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainWordCircleBinding.inflate(layoutInflater)
        dialogInputBinding= DialogInputBinding.inflate(inflater, container, false)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 두 번째 터치 시 버그남 일단 미룸
        viewBinding.oneCircle.setOnClickListener(View.OnClickListener {
            alert = AlertDialog.Builder(requireActivity()).run {
                setView(dialogInputBinding.root)

                /*dialogInputBinding.btnComplete.setOnClickListener{
                    viewBinding.textInput1.setText(dialogInputBinding.edtText.text.toString())
                    alert.dismiss()
                }*/
                /*
                setPositiveButton("완료", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        viewBinding.textInput1.setText(dialogInputBinding.edtText.text.toString())
                    }
                }
                )*/

                show()
            }
        })
        dialogInputBinding.btnComplete.setOnClickListener{
            viewBinding.oneCircle.setText(dialogInputBinding.edtText.text.toString())
            alert.dismiss()
        }
    }

}