package com.rodrigohenriques.countries.feature.countries

import com.rodrigohenriques.countries.feature.countries.job.GetCountriesJob
import com.rodrigohenriques.countries.feature.countries.job.OpenCountryDetailJob
import io.fluent.rx.RxHub
import javax.inject.Inject

class CountriesHub
@Inject constructor(
    private val getCountriesJob: GetCountriesJob,
    private val openCountryDetailJob: OpenCountryDetailJob
): RxHub<CountriesView>() {
  override fun connect(view: CountriesView) {
    getCountriesJob.bind(Unit).subscribeAndCompose()

    view.countryClicks().bind(openCountryDetailJob)
  }
}
