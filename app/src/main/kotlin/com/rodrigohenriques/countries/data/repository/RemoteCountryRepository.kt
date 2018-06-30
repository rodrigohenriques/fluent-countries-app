package com.rodrigohenriques.countries.data.repository

import com.rodrigohenriques.countries.data.api.CountriesApi
import com.rodrigohenriques.countries.data.valueobjects.Country
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class RemoteCountryRepository
@Inject constructor(
    private val countriesApi: CountriesApi,
    private val backgroundScheduler: Scheduler
) : CountryRepository {
  private val cacheableGetAllRepositories = countriesApi.getAll().cache()

  override fun getCountries(): Single<List<Country>> =
      cacheableGetAllRepositories.subscribeOn(backgroundScheduler)
}