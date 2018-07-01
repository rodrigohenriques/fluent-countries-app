package com.rodrigohenriques.countries.feature.countries.job

import android.content.Intent
import com.rodrigohenriques.countries.data.valueobjects.Country
import com.rodrigohenriques.countries.feature.countries.CountriesActivity
import com.rodrigohenriques.countries.feature.countrydetail.CountryDetailActivity
import io.fluent.rx.RxJob
import io.reactivex.Completable
import javax.inject.Inject

class OpenCountryDetailJob
@Inject constructor(
    private val activity: CountriesActivity
)  : RxJob<Country>() {
  override fun bind(input: Country): Completable {
    return Completable.fromAction {
      val intent = Intent(activity, CountryDetailActivity::class.java)
      intent.putExtra(CountryDetailActivity.COUNTRY_EXTRA, input)
      activity.startActivity(intent)
    }
  }
}