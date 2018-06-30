package com.rodrigohenriques.countries.feature.countries

import com.rodrigohenriques.countries.data.valueobjects.Country
import io.fluent.View
import io.reactivex.Observable

interface CountriesView : View<CountriesState> {

  fun countryClicks(): Observable<Country>
}