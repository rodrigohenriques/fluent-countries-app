package com.rodrigohenriques.countries.util

import android.view.View
import android.widget.ImageView
import com.rodrigohenriques.countries.BuildConfig
import com.rodrigohenriques.countries.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

fun View.show() {
  visibility = View.VISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

fun View.hide() {
  visibility = View.INVISIBLE
}

fun ImageView.load(
    path: String
) {
  val image = getTag(R.id.image_path)

  if (image != path) {
    Picasso.get().load(path)
        .fit()
        .into(this)

    setTag(R.id.image_path, path)
  }
}