package com.justagg.app.ui.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.justagg.app.R
import com.justagg.app.databinding.ActivityIntroBinding
import com.justagg.app.databinding.ActivitySplashBinding
import com.justagg.app.ui.signin.SignInActivity
import com.justagg.app.ui.signup.SignUpActivity
import com.justagg.app.util.loadImage
import com.justagg.app.util.openActivity
import com.justagg.app.util.viewBinding

class IntroActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityIntroBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadImage(R.drawable.gif_dummy, binding.ivIntro)

        binding.btnSignIn.setOnClickListener { openActivity(SignInActivity::class.java, false) }

        binding.btnCreateAccount.setOnClickListener { openActivity(SignUpActivity::class.java, false) }

    }

}