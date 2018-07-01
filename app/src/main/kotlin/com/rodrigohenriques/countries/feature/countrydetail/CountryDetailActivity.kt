package com.rodrigohenriques.countries.feature.countrydetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rodrigohenriques.countries.R
import com.rodrigohenriques.countries.data.valueobjects.Country
import com.rodrigohenriques.countries.util.load
import kotlinx.android.synthetic.main.activity_country_detail.*
import java.text.DecimalFormat

class CountryDetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_country_detail)

    val country = intent.getParcelableExtra<Country>(COUNTRY_EXTRA)

    image.load(country.flagUrl())

    setSupportActionBar(toolbar)

    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    collapsingToolbarLayout.title = country.name

    bind(country)
  }

  private fun bind(country: Country) {
    textViewCapital.text = country.capital
    textViewLanguages.text = country.languages.joinToString(", ") {
      it.name + if (it.name != it.nativeName) " (${it.nativeName})" else ""
    }

    textViewRegion.text = country.region
    textViewSubRegion.text = country.subregion

    val formatter = DecimalFormat("#,###,###")
    textViewPopulation.text = formatter.format(country.population.toInt())

    textViewCurrencies.text = country.currencies.joinToString(", ") { "${it.name} (${it.symbol})" }
  }

  companion object {
    const val COUNTRY_EXTRA = "extra_country"
  }
}
