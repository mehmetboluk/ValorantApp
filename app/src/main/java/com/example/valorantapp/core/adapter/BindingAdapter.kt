package com.example.valorantapp.core.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl : String?){
    Glide.with(this.context).load(imageUrl).centerCrop().into(this)
}