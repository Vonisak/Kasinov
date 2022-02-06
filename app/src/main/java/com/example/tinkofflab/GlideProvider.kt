package com.example.tinkofflab

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

class GlideProvider {
    fun loadGif(url: String, imageView: ImageView, activity: PostFragment, error: Int) {
        val circularProgressDrawable = activity.context?.let { CircularProgressDrawable(it) }
        circularProgressDrawable?.strokeWidth = 5f
        circularProgressDrawable?.centerRadius = 30f
        circularProgressDrawable?.start()
        Glide
            .with(activity)
            .asGif()
            .load(url)
            .placeholder(circularProgressDrawable)
            .error(error)
            .into(imageView)
    }
}