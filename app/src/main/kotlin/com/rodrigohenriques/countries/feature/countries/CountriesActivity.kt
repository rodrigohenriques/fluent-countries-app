package com.rodrigohenriques.countries.feature.countries

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.rodrigohenriques.countries.util.ErrorWithMessage
import com.rodrigohenriques.countries.R
import com.rodrigohenriques.countries.data.valueobjects.Country
import com.rodrigohenriques.countries.util.hide
import com.rodrigohenriques.countries.util.show
import dagger.android.support.DaggerAppCompatActivity
import io.fluent.Hub
import io.fluent.StateType
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_countries.*
import javax.inject.Inject

class CountriesActivity : DaggerAppCompatActivity(), CountriesView {

  @Inject
  lateinit var hub: Hub<@JvmSuppressWildcards CountriesView>
  @Inject
  lateinit var stateChanges: Observable<CountriesState>

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_countries)
    hub.connect(this)
  }

  override fun onResume() {
    super.onResume()

    stateChanges
        .observeOn(AndroidSchedulers.mainThread())
        .distinctUntilChanged()
        .doOnNext { bind(it) }
        .subscribe()
        .addTo(disposables)
  }

  override fun onPause() {
    super.onPause()
    disposables.clear()
  }

  override fun onDestroy() {
    super.onDestroy()
    hub.disconnect()
  }

  override fun countryClicks(): Observable<Country> = countriesRecyclerView.itemClicks()

  override fun bind(newState: CountriesState) {
    when (newState.type) {
      StateType.Loading -> showLoading()
      StateType.Success -> {
        hideLoading()
        showCountries(newState.countryList)
      }
      is ErrorWithMessage -> {
        hideLoading()
        showError(newState.type.message)
      }
    }
  }

  private fun showCountries(countryList: List<Country>) {
    countriesRecyclerView.bind(countryList)
  }

  private fun showError(message: String) {
    Snackbar.make(countriesRecyclerView, message, Snackbar.LENGTH_LONG).show()
  }

  private fun showLoading() {
    progressBar.show()
  }

  private fun hideLoading() {
    progressBar.hide()
  }
}
