package com.justagg.app.ui.home.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.justagg.app.R
import com.justagg.app.data.response.DummyResponse
import com.justagg.app.databinding.FragmentProfileBinding
import com.justagg.app.util.Constant

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        binding.rvSocialLinks.apply {
            adapter = ProfileAdapter(Constant.mListDummyLinks)
        }

        binding.nsvProfile.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY>oldScrollY){
                binding.fabMenu.visibility = View.GONE
            } else {
                binding.fabMenu.visibility = View.VISIBLE
            }
        }

    }

}