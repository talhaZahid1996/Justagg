package com.justagg.app.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import androidx.fragment.app.FragmentActivity
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.justagg.app.databinding.ItemLoadingBinding

object Loading {

    lateinit var dialog: Dialog

    fun FragmentActivity.showLoading(isCancelable: Boolean = false) {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = ItemLoadingBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(isCancelable)
        binding.pbLoading.indeterminateDrawable = DoubleBounce()
        try {
            dialog.show()
        } catch (e: Exception) {
            Log.e("Loading", "showLoading: ${e.printStackTrace()} ")
        }
    }

    fun FragmentActivity.cancelLoading() {
        try {
            if (::dialog.isInitialized) {
                dialog.cancel()
            }
        } catch (e: Exception) {
            Log.e("Loading", "cancelLoading: ${e.printStackTrace()}")
        }
    }

}