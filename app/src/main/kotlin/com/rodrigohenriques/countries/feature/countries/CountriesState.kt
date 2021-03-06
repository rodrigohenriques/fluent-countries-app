package com.rodrigohenriques.countries.feature.countries

import com.rodrigohenriques.countries.data.valueobjects.Country
import io.fluent.State
import io.fluent.StateType

data class CountriesState(
    val type: StateType,
    val countryList: List<Country>,
    val queriedList: List<Country>? = null,
    val query: String? = null
) : State {
  override fun type(): StateType = type

  fun setStateType(type: StateType) = copy(type = type)

  fun setCountries(countries: List<Country>): CountriesState {
    return copy(
        countryList = countries,
        queriedList = null,
        query = null
    )
  }

  fun setQuery(query: String): CountriesState {
    return copy(query = query)
  }

  fun setFilteredCountries(filteredList: List<Country>): CountriesState {
    return copy(
        queriedList = filteredList
    )
  }

  companion object {
    fun initialState() = CountriesState(
        type = StateType.Initial,
        countryList = emptyList()
    )
  }
}