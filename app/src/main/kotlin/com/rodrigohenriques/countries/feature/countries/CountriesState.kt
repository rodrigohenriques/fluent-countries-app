package com.rodrigohenriques.countries.feature.countries

import com.rodrigohenriques.countries.data.valueobjects.Country
import io.fluent.State
import io.fluent.StateType

data class CountriesState(
    val type: StateType,
    val countryList: List<Country>
) : State {
  override fun type(): StateType = type

  companion object {
    fun initialState() = CountriesState(type = StateType.Initial, countryList = emptyList())
  }
}