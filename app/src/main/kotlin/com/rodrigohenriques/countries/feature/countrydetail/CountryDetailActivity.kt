package com.rodrigohenriques.countries.feature.countrydetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.rodrigohenriques.countries.R
import com.rodrigohenriques.countries.data.valueobjects.Country
import com.rodrigohenriques.countries.util.load
import kotlinx.android.synthetic.main.activity_country_detail.*

class CountryDetailActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_country_detail)

    val country = intent.getParcelableExtra<Country>(COUNTRY_EXTRA)

    setSupportActionBar(toolbar)

    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    collapsingToolbarLayout.title = country.name

    bind(country)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        onBackPressed()
        return true
      }
    }
    return super.onOptionsItemSelected(item)
  }

  private fun bind(country: Country) {
    imageViewFlag.load(country.flagUrl())
    textViewCapital.text = country.capital
    textViewLanguages.text = country.languagesAsString()
    textViewRegion.text = country.region
    textViewSubRegion.text = country.subregion
    textViewPopulation.text = country.formattedPopulation()
    textViewCurrencies.text = country.currenciesAsString()
  }

  companion object {
    const val COUNTRY_EXTRA = "extra_country"
  }
}
