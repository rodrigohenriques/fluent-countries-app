package com.rodrigohenriques.countries.feature.main

import android.content.Context
import android.os.Bundle
import com.rodrigohenriques.countries.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

  @Inject lateinit var context: Context

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
