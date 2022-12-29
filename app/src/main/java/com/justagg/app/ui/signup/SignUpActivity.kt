package com.justagg.app.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.justagg.app.databinding.ActivitySignInBinding
import com.justagg.app.databinding.ActivitySignUpBinding
import com.justagg.app.ui.home.HomeActivity
import com.justagg.app.util.openActivity
import com.justagg.app.util.viewBinding

class SignUpActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySignUpBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ivBack.setOnClickListener { onBackPressed() }
        binding.btnCreateAccount.setOnClickListener { openActivity(HomeActivity::class.java) }
    }

}