package com.justagg.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.justagg.app.R
import com.justagg.app.databinding.ActivityIntroBinding
import com.justagg.app.databinding.ActivityMainBinding
import com.justagg.app.util.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}