package com.rodrigohenriques.countries.feature.countries

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
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
import android.support.v7.widget.SearchView
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class CountriesActivity : DaggerAppCompatActivity(), CountriesView {

  @Inject
  lateinit var hub: Hub<@JvmSuppressWildcards CountriesView>
  @Inject
  lateinit var stateChanges: Observable<CountriesState>

  private var searchQueryUpdates = PublishSubject.create<CharSequence>()

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_countries)
    setSupportActionBar(toolbar)
    hub.connect(this)

    stateChanges
        .observeOn(AndroidSchedulers.mainThread())
        .distinctUntilChanged()
        .doOnNext { bind(it) }
        .subscribe()
        .addTo(disposables)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_countries, menu)
    val searchView = menu.findItem(R.id.option_search).actionView as SearchView

    RxSearchView.queryTextChanges(searchView)
        .doOnSubscribe { it.addTo(disposables) }
        .subscribe(searchQueryUpdates)

    return true
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
    hub.disconnect()
  }

  override fun countryClicks(): Observable<Country> = countriesRecyclerView.itemClicks()

  override fun searchQueryUpdates(): Observable<CharSequence> =
      searchQueryUpdates.debounce(300, TimeUnit.MILLISECONDS)

  override fun bind(newState: CountriesState) {
    when (newState.type) {
      StateType.Loading -> showLoading()
      StateType.Success -> {
        hideLoading()
        showCountries(newState.queriedList ?: newState.countryList)
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
