package com.example.taler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taler.databinding.FragmentListItemAddFriendsBinding

class ListItemAddFriendsFragment : Fragment() {

    private lateinit var viewBinding : FragmentListItemAddFriendsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentListItemAddFriendsBinding.inflate(layoutInflater)
        return viewBinding.root
    }

}