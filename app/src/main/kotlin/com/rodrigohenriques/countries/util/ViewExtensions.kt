package com.rodrigohenriques.countries.util

import android.view.View
import android.widget.ImageView
import com.rodrigohenriques.countries.BuildConfig
import com.rodrigohenriques.countries.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

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
    path: String,
    transformation: Transformation? = RoundedCornersTransformation(8, 0),
    additionalSetupFunction: RequestCreator.() -> RequestCreator = { this }
) {
  val image = getTag(R.id.image_path)

  if (image != path) {
    val picasso = Picasso.get()

    picasso.isLoggingEnabled = BuildConfig.DEBUG
    picasso.setIndicatorsEnabled(BuildConfig.DEBUG)

    val requestCreator = picasso
        .load(path)
        .additionalSetupFunction()

    if (transformation != null) {
      requestCreator.transform(transformation)
    }

    requestCreator.into(this)

    setTag(R.id.image_path, path)
  }
}