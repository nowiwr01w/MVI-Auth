package com.nowiwr01.languator.base

import android.view.View

interface ViewProvider<T : View> {
    val view: T
}