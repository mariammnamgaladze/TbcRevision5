package com.example.tbcrevision5

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String?){
    Glide.with(this).load(url).into(this)
}