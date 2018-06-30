package com.rodrigohenriques.countries.di.modules

import android.content.Context
import com.rodrigohenriques.countries.BuildConfig
import com.rodrigohenriques.countries.data.api.CountriesApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

  @Provides
  fun provideCache(context: Context): Cache {
    return Cache(context.cacheDir, 10 * 1024 * 1024)
  }

  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()

    httpLoggingInterceptor.level = when {
      BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
      else -> HttpLoggingInterceptor.Level.NONE
    }

    return httpLoggingInterceptor
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(
      cache: Cache,
      httpLoggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(cache) // 10 MB
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