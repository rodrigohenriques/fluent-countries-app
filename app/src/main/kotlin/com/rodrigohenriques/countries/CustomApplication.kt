package com.rodrigohenriques.countries

import com.rodrigohenriques.countries.di.components.ApplicationComponent
import com.rodrigohenriques.countries.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class CustomApplication : DaggerApplication() {
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    applicationComponent = DaggerApplicationComponent.builder()
        .application(this)
        .build()

    applicationComponent.inject(this)

    return applicationComponent
  }

  companion object {
    lateinit var applicationComponent: ApplicationComponent
  }
}
