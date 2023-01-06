package com.justagg.app.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.justagg.app.R
import com.justagg.app.databinding.ActivitySplashBinding
import com.justagg.app.ui.intro.IntroActivity
import com.justagg.app.util.loadImage
import com.justagg.app.util.openActivity
import com.justagg.app.util.snackBar
import com.justagg.app.util.viewBinding
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)
    private var i = 0
    private var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadImage(R.drawable.ic_logo, binding.ivLogo)

        timer.schedule(2000) {
            openActivity(IntroActivity::class.java)
        }

    }

    override fun onBackPressed() {
        if (i == 0) {
            i++
            binding.root.snackBar("Press back again to exit.")
        } else {
            val a = Intent(Intent.ACTION_MAIN)
            a.addCategory(Intent.CATEGORY_HOME)
            a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(a)
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }

}