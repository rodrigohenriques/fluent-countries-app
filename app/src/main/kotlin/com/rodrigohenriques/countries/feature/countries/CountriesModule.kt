package com.rodrigohenriques.countries.feature.countries

import com.rodrigohenriques.countries.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides
import io.fluent.Hub
import io.fluent.rx.RxStore

@Module
class CountriesModule {
  @Provides
  fun provideView(activity: CountriesActivity): CountriesView = activity

  @Provides
  @ActivityScope
  fun providesHub(countriesHub: CountriesHub): Hub<CountriesView> = countriesHub

  @Provides
  @ActivityScope
  fun provideStore(): RxStore<CountriesState> = RxStore(CountriesState.initialState())

  @Provides
  @ActivityScope
  fun provideStateChanges(store: RxStore<CountriesState>) = store.stateChanges()
}
