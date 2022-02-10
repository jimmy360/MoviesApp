package com.example.moviesapp.framework.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String) {
      Glide.with(context).load(url).into(this)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true) {
    LayoutInflater.from(context).inflate(layoutRes,this,attachToRoot)
}
