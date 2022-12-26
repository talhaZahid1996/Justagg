package com.justagg.app.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.justagg.app.R
import com.justagg.app.databinding.ActivitySplashBinding
import com.justagg.app.ui.intro.IntroActivity
import com.justagg.app.util.loadImage
import com.justagg.app.util.openActivity
import com.justagg.app.util.viewBinding

class SplashActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadImage(R.drawable.ic_logo, binding.ivLogo)

        binding.ivLogo.setOnClickListener { openActivity(IntroActivity::class.java) }

    }

}