package com.justagg.app.ui.home.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.justagg.app.R
import com.justagg.app.databinding.FragmentMenuBinding
import com.justagg.app.databinding.FragmentProfileBinding
import com.justagg.app.util.viewBinding

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)
    }

}