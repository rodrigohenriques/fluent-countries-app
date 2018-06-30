package com.rodrigohenriques.countries.di.modules

import com.rodrigohenriques.countries.di.scopes.ActivityScope
import com.rodrigohenriques.countries.feature.countries.CountriesActivity
import com.rodrigohenriques.countries.feature.countries.CountriesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = [ CountriesModule::class ])
  internal abstract fun mainActivity(): CountriesActivity
}
