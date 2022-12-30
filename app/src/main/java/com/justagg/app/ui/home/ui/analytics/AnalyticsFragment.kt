package com.justagg.app.ui.home.ui.analytics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.justagg.app.R
import com.justagg.app.databinding.FragmentAnalyticsBinding
import com.justagg.app.util.Constant

class AnalyticsFragment : Fragment(R.layout.fragment_analytics) {

    private lateinit var binding: FragmentAnalyticsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAnalyticsBinding.bind(view)

        binding.rvSocialLinks.apply {
            adapter = AnalyticsAdapter(Constant.mListDummyLinks)
        }

    }

}