package com.justagg.app.ui.signin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.justagg.app.databinding.ActivitySignInBinding
import com.justagg.app.ui.home.HomeActivity
import com.justagg.app.util.openActivity
import com.justagg.app.util.viewBinding

class SignInActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySignInBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ivBack.setOnClickListener { onBackPressed() }
        binding.btnSignIn.setOnClickListener { openActivity(HomeActivity::class.java) }

    }
}