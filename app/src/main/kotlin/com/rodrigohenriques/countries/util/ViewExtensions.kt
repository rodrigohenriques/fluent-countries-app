package com.rodrigohenriques.countries.util

import android.view.View

fun View.show() {
  visibility = View.VISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

fun View.hide() {
  visibility = View.INVISIBLE
}
