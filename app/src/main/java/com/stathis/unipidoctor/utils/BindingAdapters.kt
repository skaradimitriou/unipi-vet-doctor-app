package com.stathis.unipidoctor.utils

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stathis.unipidoctor.R

@BindingAdapter("setProfileImage")
fun ImageView.setProfileImage(url: String?) {
    try {
        Glide.with(this).load(url)
            .placeholder(R.drawable.profile_placeholder)
            .error(R.drawable.profile_placeholder)
            .into(this)
    } catch (e: Exception) {
        setImageResource(R.drawable.profile_placeholder)
    }
}

@BindingAdapter("loadIcon")
fun ImageView.loadIcon(iconId: Int) {
    val drawable = AppCompatResources.getDrawable(context, iconId)
    setImageDrawable(drawable)
}