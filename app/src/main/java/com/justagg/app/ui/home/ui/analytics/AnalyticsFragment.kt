package com.justagg.app.ui.home.ui.analytics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.justagg.app.R
import com.justagg.app.databinding.FragmentAnalyticsBinding
import com.justagg.app.util.Constant
import com.justagg.app.util.displayPopUp

class AnalyticsFragment : Fragment(R.layout.fragment_analytics) {

    private lateinit var binding: FragmentAnalyticsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAnalyticsBinding.bind(view)

        binding.rvSocialLinks.apply {
            adapter = AnalyticsAdapter(Constant.mListDummyLinks)
        }

        binding.ivInfoViews.setOnClickListener {
            requireActivity().displayPopUp(
                "Information",
                "Total views of your profile"
            )
        }

        binding.ivInfoClicks.setOnClickListener {
            requireActivity().displayPopUp(
                "Information",
                "Total clicks on your profile"
            )
        }

        binding.ivInfoContacts.setOnClickListener {
            requireActivity().displayPopUp(
                "Information",
                "Total contacts added in your account."
            )
        }

        binding.ivInfoClickRatePercentage.setOnClickListener {
            requireActivity().displayPopUp(
                "Information",
                "Total clicks percentage on your profile"
            )
        }

    }

}