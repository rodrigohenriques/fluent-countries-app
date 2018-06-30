package com.rodrigohenriques.countries.feature.countries

import io.fluent.rx.RxHub
import javax.inject.Inject

class CountriesHub
@Inject constructor(): RxHub<CountriesView>() {
  override fun connect(view: CountriesView) {

  }
}
