package com.project.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {
  @Binds
  abstract fun provideApplicationContext(application: Application): Context
}