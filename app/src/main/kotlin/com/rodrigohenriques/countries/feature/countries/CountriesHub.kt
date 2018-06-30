package com.rodrigohenriques.countries.feature.countries

import com.rodrigohenriques.countries.feature.countries.job.GetCountriesJob
import io.fluent.rx.RxHub
import javax.inject.Inject

class CountriesHub
@Inject constructor(
    private val getCountriesJob: GetCountriesJob
): RxHub<CountriesView>() {
  override fun connect(view: CountriesView) {
    getCountriesJob.bind(Unit).subscribeAndCompose()
  }
}
