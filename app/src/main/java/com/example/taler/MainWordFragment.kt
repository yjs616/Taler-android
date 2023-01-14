package com.example.taler

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
import com.example.taler.databinding.FragmentMainWordBinding


class MainWordFragment : Fragment() {

    private lateinit var viewBinding: FragmentMainWordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainWordBinding.inflate(layoutInflater)
        return viewBinding.root


    }

}