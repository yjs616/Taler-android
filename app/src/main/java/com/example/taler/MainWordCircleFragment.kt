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
import androidx.core.view.children
import com.ddd.androidutils.DoubleClick
import com.ddd.androidutils.DoubleClickListener
import com.example.taler.databinding.FragmentMainWordCircleBinding


class MainWordCircleFragment : Fragment() {

    private lateinit var viewBinding: FragmentMainWordCircleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainWordCircleBinding.inflate(layoutInflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.oneCircle.setOnClickListener(View.OnClickListener {
            //viewBinding.twoCircle.setBackgroundResource(R.color.black)
            childFragmentManager
                .beginTransaction().apply {
                    replace(R.id.frame_fragment,MainWordAddText())
                    commit()
                }
        })



    }

}