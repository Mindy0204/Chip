package com.mindyhsu.chip.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mindyhsu.chip.R

fun ImageView.glide(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .apply(RequestOptions().error(R.drawable.iv_character))
        .centerCrop()
        .into(this)
}