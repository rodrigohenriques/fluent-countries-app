package com.rodrigohenriques.countries.feature.countries

import android.os.Bundle
import com.rodrigohenriques.countries.R
import dagger.android.support.DaggerAppCompatActivity
import io.fluent.Hub
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CountriesActivity : DaggerAppCompatActivity(), CountriesView {

  @Inject
  lateinit var hub: Hub<@JvmSuppressWildcards CountriesView>
  @Inject
  lateinit var stateChanges: Observable<CountriesState>

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
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

  override fun bind(newState: CountriesState) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
