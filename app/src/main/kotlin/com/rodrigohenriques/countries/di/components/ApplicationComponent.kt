package com.rodrigohenriques.countries.di.components

import android.app.Application
import com.rodrigohenriques.countries.di.modules.ActivityBuilderModule
import com.rodrigohenriques.countries.di.modules.ApplicationModule
import com.rodrigohenriques.countries.CustomApplication
import com.rodrigohenriques.countries.di.modules.ApiModule
import com.rodrigohenriques.countries.di.modules.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      AndroidSupportInjectionModule::class,
      ApplicationModule::class,
      ActivityBuilderModule::class,
      ApiModule::class,
      RepositoryModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CustomApplication> {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): ApplicationComponent
  }
}
