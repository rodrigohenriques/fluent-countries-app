package com.project.di.components

import android.app.Application
import com.project.di.modules.ActivityBuilderModule
import com.project.di.modules.ApplicationModule
import com.project.CustomApplication
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
      ActivityBuilderModule::class
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
