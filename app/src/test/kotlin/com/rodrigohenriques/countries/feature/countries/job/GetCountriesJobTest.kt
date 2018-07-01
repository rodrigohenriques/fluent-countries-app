package com.rodrigohenriques.countries.feature.countries.job

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.rodrigohenriques.countries.data.FakeData
import com.rodrigohenriques.countries.data.repository.CountryRepository
import com.rodrigohenriques.countries.feature.countries.CountriesState
import com.rodrigohenriques.countries.util.ErrorWithMessage
import io.fluent.StateType
import io.fluent.rx.RxStore
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCountriesJobTest {

  @Mock
  lateinit var repository: CountryRepository

  @Spy
  val store = RxStore(CountriesState.initialState())

  @InjectMocks
  lateinit var getCountriesJob: GetCountriesJob

  @Test
  fun bind_successfulRequest_shouldUpdateStoreWithResultList() {
    val countries = FakeData.countries()

    whenever(repository.getCountries())
        .thenReturn(Single.fromCallable { countries })

    val stateObserver = store.stateChanges().test()
    val jobObserver =  getCountriesJob.bind(Unit).test()

    verify(repository).getCountries()

    jobObserver.assertComplete()
    stateObserver.assertNotComplete()
    stateObserver.assertValueAt(0) { it.type == StateType.Loading && it.countryList.isEmpty()}
    stateObserver.assertValueAt(1) { it.type == StateType.Success && it.countryList == countries }
    stateObserver.assertValueCount(2)
  }

  @Test
  fun bind_failingRequest_shouldUpdateStoreWithResultList() {
    whenever(repository.getCountries())
        .thenReturn(Single.error(Exception("Failure")))

    val stateObserver = store.stateChanges().test()
    val jobObserver =  getCountriesJob.bind(Unit).test()

    verify(repository).getCountries()

    jobObserver.assertComplete()
    stateObserver.assertNotComplete()
    stateObserver.assertValueAt(0) { it.type == StateType.Loading }
    stateObserver.assertValueAt(1) { it.type is ErrorWithMessage }
    stateObserver.assertValueCount(2)
  }
}