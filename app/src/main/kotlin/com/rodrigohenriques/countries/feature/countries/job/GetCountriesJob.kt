package com.rodrigohenriques.countries.feature.countries.job

import com.rodrigohenriques.countries.util.ErrorWithMessage
import com.rodrigohenriques.countries.data.repository.CountryRepository
import com.rodrigohenriques.countries.feature.countries.CountriesState
import io.fluent.StateType
import io.fluent.rx.RxJob
import io.fluent.rx.RxStore
import io.reactivex.Completable
import javax.inject.Inject

class GetCountriesJob
@Inject constructor(
    private val countryRepository: CountryRepository,
    private val store: RxStore<CountriesState>
) : RxJob<Unit>() {
  override fun bind(input: Unit): Completable {
    return countryRepository.getCountries()
        .doOnSubscribe { store.update { copy(type = StateType.Loading) } }
        .doOnSuccess { countries ->
          store.update {
            copy(
                type = StateType.Success,
                countryList = countries
            )
          }
        }
        .doOnError {
          store.update {
            copy(type = ErrorWithMessage("Can't get new countries"))
          }
        }
        .ignoreElement()
  }
}