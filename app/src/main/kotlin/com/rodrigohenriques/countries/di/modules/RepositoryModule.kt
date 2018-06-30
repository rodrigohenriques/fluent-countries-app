package com.rodrigohenriques.countries.di.modules

import com.rodrigohenriques.countries.data.repository.CountryRepository
import com.rodrigohenriques.countries.data.repository.RemoteCountryRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
  @Provides
  @Singleton
  fun provideCountryRepository(
      remoteCountryRepository: RemoteCountryRepository
  ): CountryRepository = remoteCountryRepository
}