package com.rodrigohenriques.countries.feature.countries

import android.content.Context
import android.content.res.Configuration
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import com.rodrigohenriques.countries.data.valueobjects.Country
import io.reactivex.Observable

class CountriesRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

  private val countriesAdapter = CountriesAdapter()

  init {
    val spanCount = when (resources.configuration.orientation) {
      Configuration.ORIENTATION_LANDSCAPE -> 4
      else -> 2
    }

    layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
    adapter = countriesAdapter
  }

  fun bind(countryList: List<Country>) {
    countriesAdapter.changeData(countryList)
  }

  fun itemClicks(): Observable<Country> = countriesAdapter.itemClicks()
}