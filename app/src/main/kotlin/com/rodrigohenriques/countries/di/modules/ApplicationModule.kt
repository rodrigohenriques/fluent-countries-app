package com.rodrigohenriques.countries.di.modules

import android.app.Application
import android.content.Context
import com.rodrigohenriques.countries.BuildConfig
import com.rodrigohenriques.countries.data.api.CountriesApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApplicationModule {
  @Provides
  fun provideApplicationContext(application: Application): Context = application
}