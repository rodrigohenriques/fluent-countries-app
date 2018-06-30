package com.rodrigohenriques.countries.di.modules

import com.rodrigohenriques.countries.BuildConfig
import com.rodrigohenriques.countries.data.api.CountriesApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()

    httpLoggingInterceptor.level = when {
      BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
      else -> HttpLoggingInterceptor.Level.NONE
    }

    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
  }

  @Provides
  @Singleton
  fun providesCountriesApi(client: OkHttpClient) : CountriesApi {
    return Retrofit.Builder()
        .baseUrl(CountriesApi.URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(CountriesApi::class.java)
  }
}