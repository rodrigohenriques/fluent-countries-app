package com.rodrigohenriques.countries.feature.countries.job

import com.rodrigohenriques.countries.feature.countries.CountriesState
import io.fluent.rx.RxJob
import io.fluent.rx.RxStore
import io.reactivex.Completable
import io.reactivex.Scheduler
import javax.inject.Inject

class SearchCountryByNameJob
@Inject constructor(
    private val store: RxStore<CountriesState>,
    private val backgroundScheduler: Scheduler
) : RxJob<String>() {
  override fun bind(input: String): Completable {
    val query = input.trim()

    if (query.isEmpty()) return Completable.complete()

    return Completable.fromAction {
      val state = store.state()
      val filteredList = state.countryList.filter { country ->
        country.name.startsWith(query, ignoreCase = true) ||
            country.nativeName.startsWith(query, ignoreCase = true)
      }

      store.update {
        setQuery(query)
            .setFilteredCountries(filteredList)
      }
    }.subscribeOn(backgroundScheduler)
  }
}
