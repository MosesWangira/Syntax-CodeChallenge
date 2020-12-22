@file:Suppress("DEPRECATION")

package com.example.syntaxcodechallenge.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.example.syntaxcodechallenge.R
import com.google.android.material.snackbar.Snackbar

/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("isNetworkError", "playlist")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, list: Any?) {
    view.visibility = if (list != null) View.GONE else View.VISIBLE

    if(isNetWorkError) {
        view.visibility = View.GONE
    }
}

/**
 * Animating list being displayed
 * */
fun animate(context: Context, resId: Int): LayoutAnimationController {
    return AnimationUtils.loadLayoutAnimation(context, resId)
}