package com.rodrigohenriques.countries.di.modules

import com.rodrigohenriques.countries.di.scopes.ActivityScope
import com.rodrigohenriques.countries.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = [ MainActivityModule::class ])
  internal abstract fun mainActivity(): MainActivity
}
