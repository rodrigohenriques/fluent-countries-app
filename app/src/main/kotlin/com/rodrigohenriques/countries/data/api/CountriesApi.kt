package com.rodrigohenriques.countries.data.api

import com.rodrigohenriques.countries.data.valueobjects.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {
  @GET("all")
  fun getAll(): Single<List<Country>>

  companion object {
    const val URL = "https://restcountries.eu/rest/v2/"
  }
}