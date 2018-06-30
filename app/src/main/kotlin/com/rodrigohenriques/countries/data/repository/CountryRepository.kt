package com.rodrigohenriques.countries.data.repository

import com.rodrigohenriques.countries.data.valueobjects.Country
import io.reactivex.Single

interface CountryRepository {
  fun getCountries(): Single<List<Country>>
}