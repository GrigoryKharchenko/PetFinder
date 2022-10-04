package com.example.petfinder.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.petfinder.R

fun ImageView.loadPhoto(uri: String) {
    Glide.with(this)
        .load(uri)
        .placeholder(R.drawable.photo)
        .transform(RoundedCorners(48))
        .into(this)
}