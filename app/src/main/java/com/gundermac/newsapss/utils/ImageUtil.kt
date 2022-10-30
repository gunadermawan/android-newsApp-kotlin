package com.gundermac.newsapss.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gundermac.newsapss.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, urlString: String?) {
    urlString.let {
        Glide.with(imageView)
            .load(urlString)
            .placeholder(R.drawable.ic_news)
            .error(R.drawable.ic_news)
            .into(imageView)
    }
}