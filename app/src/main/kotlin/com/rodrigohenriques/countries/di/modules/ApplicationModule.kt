package com.rodrigohenriques.countries.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

@Module
class ApplicationModule {
  @Provides
  fun provideApplicationContext(application: Application): Context = application

  @Provides
  fun provideBackgroundScheduler(): Scheduler = Schedulers.io()
}