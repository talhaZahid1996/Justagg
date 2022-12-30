package com.justagg.app.ui.home.ui.contact

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.justagg.app.R
import com.justagg.app.data.response.DummyResponse
import com.justagg.app.databinding.FragmentContactBinding
import com.justagg.app.databinding.FragmentProfileBinding
import com.justagg.app.util.viewBinding

class ContactFragment : Fragment(R.layout.fragment_contact) {

    private lateinit var binding: FragmentContactBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContactBinding.bind(view)

        binding.rvContacts.apply {
            adapter = ContactsAdapter(
                mutableListOf(
                    DummyResponse(
                        name = "Justagg",
                        designation = "Justagg",
                        date = "Wed, 3/28",
                        status = true,
                        icon = R.drawable.ic_dummy_user,
                    ),
                    DummyResponse(
                        name = "Deep",
                        designation = "CEO",
                        date = "Wed, 3/28",
                        icon = R.drawable.ic_dummy_user,
                        status = true
                    ),
                    DummyResponse(
                        name = "Daood",
                        designation = "Admin",
                        date = "Wed, 3/28",
                        icon = R.drawable.ic_dummy_user,
                        status = true
                    ),
                    DummyResponse(
                        name = "Roser James",
                        designation = "IT",
                        date = "Wed, 3/28",
                        icon = R.drawable.ic_dummy_user,
                        status = false
                    ),
                    DummyResponse(
                        name = "Kabeer",
                        designation = "CEO",
                        date = "Wed, 3/28",
                        icon = R.drawable.ic_dummy_user,
                        status = false
                    ),
                    DummyResponse(
                        name = "Talha",
                        designation = "COO",
                        date = "Mon, 1/28",
                        icon = R.drawable.ic_dummy_user,
                        status = true
                    ),
                )
            )
        }

    }
}