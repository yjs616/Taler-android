package com.example.taler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taler.databinding.FragmentListItemAddBookBinding


class ListItemAddBookFragment : Fragment() {
    private lateinit var viewBinding: FragmentListItemAddBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentListItemAddBookBinding.inflate(layoutInflater)
        return viewBinding.root
    }

}