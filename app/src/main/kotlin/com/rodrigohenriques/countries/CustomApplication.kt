package com.rodrigohenriques.countries

import com.rodrigohenriques.countries.di.components.ApplicationComponent
import com.rodrigohenriques.countries.di.components.DaggerApplicationComponent
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import okhttp3.Cache
import okhttp3.OkHttpClient

class CustomApplication : DaggerApplication() {
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    applicationComponent = DaggerApplicationComponent.builder()
        .application(this)
        .build()

    applicationComponent.inject(this)

    setupPicasso()

    return applicationComponent
  }

  private fun setupPicasso() {
    val picassoClient = OkHttpClient.Builder()
        .addInterceptor {chain ->
          val originalResponse = chain.proceed(chain.request())
          val newBuilder = originalResponse.newBuilder()
          val cacheMaxAge = 60 * 60 * 24 * 365
          val header = newBuilder.header("Cache-Control", "max-age=$cacheMaxAge")
          header.build()
        }
        .cache(Cache(cacheDir, 10 * 1024 * 1024))
        .build()

    val downloader = OkHttp3Downloader(picassoClient)

    val picasso = Picasso.Builder(this)
        .downloader(downloader)
        .build()

    Picasso.setSingletonInstance(picasso)
  }

  companion object {
    lateinit var applicationComponent: ApplicationComponent
  }
}
