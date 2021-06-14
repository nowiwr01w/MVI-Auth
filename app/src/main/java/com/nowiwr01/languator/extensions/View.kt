package com.nowiwr01.languator.extensions

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.nowiwr01.languator.R

fun View?.setVisible() {
    this?.visibility = View.VISIBLE
}

fun View?.setGone() {
    this?.visibility = View.GONE
}

fun EditText.setError() {
    setBackgroundResource(R.drawable.bg_edit_text_error)
}

fun EditText.setDefault() {
    setBackgroundResource(R.drawable.bg_edit_text_default)
}

fun Fragment.showSnackbar(message: String) {
    this.requireContext().showSnackbar(this.requireView(), message)
}

fun Context.showSnackbar(view: View, message: String) {
    val custom = LayoutInflater.from(this).inflate(R.layout.layout_custom_snackbar, null)
    custom.apply {
        elevation = 0f
        background = ContextCompat.getDrawable(this@showSnackbar, R.drawable.bg_snackbar)
    }
    custom.findViewById<TextView>(R.id.customText).apply {
        text = message
        setTextColor(this@showSnackbar.getColor(R.color.stackBar_color))
    }
    val snackBar = Snackbar.make(view, "", 2500).apply {
        getView().setPadding(0, 0, 0, 0)
        (getView() as Snackbar.SnackbarLayout).removeAllViews()
        (getView() as Snackbar.SnackbarLayout).addView(custom)
    }
    snackBar.show()
}

fun EditText.doOnTextChanged(callback: () -> Unit) {
    this.doOnTextChanged { _, _, _, _ ->
        callback.invoke()
    }
}