package com.justagg.app.util

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.justagg.app.R
import com.justagg.app.data.network.Resource
import com.justagg.app.databinding.ItemDialogUserOptionBinding
import com.justagg.app.databinding.ItemGeneralAlertDialogBinding
import com.justagg.app.util.Loading.cancelLoading
import java.util.regex.Pattern

private val TAG = "Utils"

fun <A : Activity> FragmentActivity.openActivity(
    activity: Class<A>,
    newAct: Boolean = true,
    extras: Bundle.() -> Unit = {}
) {
    val intent = Intent(this, activity)
    intent.putExtras(Bundle().apply(extras))
    if (newAct) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
    startActivity(intent)
}

fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, message, 5000)
    action?.let {
        snackBar.setAction("Yes") {
            it()
        }
    }
    snackBar.show()
}

inline fun Context.toast(message: () -> String) {
    Toast.makeText(this, message(), Toast.LENGTH_LONG).show()
}

inline fun Fragment.toast(message: () -> String) {
    Toast.makeText(this.context, message(), Toast.LENGTH_LONG).show()
}

fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.color = ContextCompat.getColor(this@makeLinks.context, R.color.black)
                textPaint.isUnderlineText = true
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                view.setBackgroundColor(Color.TRANSPARENT)
                view.highlightColor = Color.TRANSPARENT
                link.second.onClick(view)
            }

        }
        startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance()
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}

fun TextInputLayout.validate(): Boolean {
    val result = this.editText?.text.toString().trim { it <= ' ' }
    if (result.isEmpty()) {
        this.error = context.resources.getString(R.string.editTextEmptyFieldError)
        this.requestFocus()
        return false
    }
    return true
}

fun TextInputLayout.isEmailValid(): Boolean {
    val email = this.editText?.text.toString().trim { it <= ' ' }.lowercase()

    if (email.isEmpty()) {
        this.error = context.resources.getString(R.string.editTextEmptyFieldError)
        this.requestFocus()
        return false
    }

    if (emailPattern(email)) {
        this.error = context.resources.getString(R.string.emailFormatError)
        this.requestFocus()
        return false
    }
    return true
}

private fun emailPattern(email: String): Boolean {
    var isValid = false
    val expression = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
    val inputStr: CharSequence = email
    val pattern = Pattern.compile(
        expression,
        Pattern.CASE_INSENSITIVE
    )
    val matcher = pattern.matcher(inputStr)
    if (!matcher.matches()) {
        isValid = true
    }
    return isValid
}

fun TextInputLayout.isPasswordValid(): Boolean {
    val password = this.editText?.text.toString().trim { it <= ' ' }
    if (password.isEmpty()) {
        this.error = context.resources.getString(R.string.editTextEmptyFieldError)
        this.requestFocus()
        return false
    }
    if (password.length < 6) {
        this.error = context.resources.getString(R.string.passwordShortLength)
        this.requestFocus()
        return false
    }

    return true
}

fun TextInputLayout.value(isLower: Boolean = false): String {
    return if (isLower) {
        editText?.text.toString().trim().lowercase()
    } else {
        editText?.text.toString().trim()
    }
}

fun FragmentActivity.displayPopUp(
    title: String,
    subTitle: String?,
    onClick: GeneralListener? = null
) {
    cancelLoading()
    val binding = ItemGeneralAlertDialogBinding.inflate(layoutInflater)
    val builder = AlertDialog.Builder(this)
    builder.setView(binding.root)
    val dialog = builder.create()

    dialog.window?.decorView?.setBackgroundResource(R.drawable.alert_dialog_background)
    dialog.show()

    binding.tvTitlePopUp.text = title
    binding.tvSubTitlePopUp.text = subTitle

    when (title) {
        getString(R.string.error) -> {
            binding.btnOkPopUp.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.holo_red_light
                )
            )
            binding.civAlert.setColorFilter(
                ContextCompat.getColor(this, R.color.holo_red_light),
                android.graphics.PorterDuff.Mode.MULTIPLY
            )
            binding.civAlert.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_failure
                )
            )
        }
        getString(R.string.alert) -> {
            binding.tvTitlePopUp.text = resources.getString(R.string.alert)
            binding.btnOkPopUp.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.black
                )
            )
            binding.civAlert.setColorFilter(
                ContextCompat.getColor(this, R.color.black),
                android.graphics.PorterDuff.Mode.MULTIPLY
            )
            binding.civAlert.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_success
                )
            )
        }
        else -> {
            binding.btnOkPopUp.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.holo_green_light
                )
            )
            binding.civAlert.setColorFilter(
                ContextCompat.getColor(this, R.color.holo_green_light),
                android.graphics.PorterDuff.Mode.MULTIPLY
            )
            binding.civAlert.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_success
                )
            )
        }
    }

    binding.btnOkPopUp.setOnClickListener {
        if (onClick == null) {
            dialog.cancel()
        } else {
            onClick.buttonClick(true)
            dialog.cancel()
        }
    }

}

fun FragmentActivity.displayPopUpOptions(
    onClick: GeneralListener? = null,
    message: String = "",
    isCancelable: Boolean = true
) {
    val binding = ItemDialogUserOptionBinding.inflate(layoutInflater)
    val builder = AlertDialog.Builder(this)
    builder.setCancelable(isCancelable)
    builder.setView(binding.root)
    val dialog = builder.create()
    dialog.window?.decorView?.setBackgroundResource(R.drawable.alert_dialog_background)
    dialog.show()

    if (message.isNotEmpty()) {
        binding.tvSubTitlePopUp.text = message
    }

    binding.btnOkPopUp.setOnClickListener {
        dialog.cancel()
        onClick?.let { onClick.buttonClick(true) }
    }

    binding.btnCancel.setOnClickListener { dialog.cancel() }

}

fun FragmentActivity.drawable(icon: Int): Drawable? {
    return ResourcesCompat.getDrawable(resources, icon, null)
}

fun FragmentActivity.isOnline(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}

fun loadImage(image: Any?, imageView: ImageView) {

    try {
        Glide.with(imageView.context)
            .load(image)
            .placeholder(R.drawable.ic_image_placeholder)
            .into(imageView)
    } catch (e: Exception) {
        Log.e(TAG, "loadImage: ${e.printStackTrace()}")
    }

}

fun TextView.copyOnHold(customText: String? = null) {
    setOnClickListener {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", customText ?: text)
        clipboardManager.setPrimaryClip(clip)
        it.context.toast { "Link Copied" }
    }
}

fun FragmentActivity.openBrowser(url: String) {
    if (url.startsWith("https://") || url.startsWith("http://")) {
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    } else {
        toast { "Invalid Url" }
    }
}

fun FragmentActivity.contactUs() {
    val email = "support@tappze.com"
    val subject = "Support"
    val body = "Please send us your issue in detail, we're happy to learn from you"

    val selectorIntent = Intent(Intent.ACTION_SENDTO)
    val urlString =
        "mailto:" + Uri.encode(email) + "?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(
            body
        )
    selectorIntent.data = Uri.parse(urlString)

    val emailIntent = Intent(Intent.ACTION_SEND)
    emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
    emailIntent.putExtra(Intent.EXTRA_TEXT, body)
    emailIntent.selector = selectorIntent

    startActivity(Intent.createChooser(emailIntent, "Send email"))
}

fun FragmentActivity.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    val errorMessage = when {
        failure.isNetworkError -> {
            "Please check your internet connect."
        }
        failure.errorCode == 401 || failure.errorCode == 400 ||
                failure.errorCode == 402 || failure.errorCode == 403 ||
                failure.errorCode == 404 -> {
            "Not Found"
        }
        failure.throwable?.isNotEmpty() == true -> {
            failure.throwable
        }
        else -> {
            "Not Found"
        }
    }

    if (errorMessage == "connect timed out") {
        displayPopUp(
            Constant.k_Error,
            "Timeout error.\nPlease tap the button to retry.",
            object : GeneralListener {
                override fun buttonClick(clicked: Boolean) {
                    retry?.let { it() }
                }
            })
        return
    }

    displayPopUp(
        Constant.k_Error,
        errorMessage
    )
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

internal inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)