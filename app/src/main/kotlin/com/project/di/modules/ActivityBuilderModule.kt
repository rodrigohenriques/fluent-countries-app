package com.project.di.modules

import com.project.di.scopes.ActivityScope
import com.project.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = [ MainActivityModule::class ])
  internal abstract fun mainActivity(): MainActivity
}
