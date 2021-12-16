package com.example.github.ui.trendings

import android.net.Uri
import android.widget.ImageView
import com.example.github.R
import com.squareup.picasso.Picasso

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        Picasso
                .get()
                .load(Uri.parse(url))
                .placeholder(R.drawable.ic_octoface)
                .error(R.drawable.ic_octoface)
                .centerCrop()
                .fit()
                .into(view)
    }

}