package com.justagg.app.ui.home.ui.share

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.justagg.app.R
import com.justagg.app.databinding.FragmentProfileBinding
import com.justagg.app.databinding.FragmentShareBinding
import com.justagg.app.util.viewBinding

class ShareFragment : Fragment(R.layout.fragment_share) {

    private lateinit var binding: FragmentShareBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShareBinding.bind(view)
    }

}