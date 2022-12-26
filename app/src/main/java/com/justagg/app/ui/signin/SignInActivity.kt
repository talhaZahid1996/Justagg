package com.justagg.app.ui.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.justagg.app.databinding.ActivityIntroBinding
import com.justagg.app.databinding.ActivitySignInBinding
import com.justagg.app.util.viewBinding

class SignInActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySignInBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}