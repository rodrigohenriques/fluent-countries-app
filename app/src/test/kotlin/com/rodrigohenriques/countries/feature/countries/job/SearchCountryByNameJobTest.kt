package com.rodrigohenriques.countries.feature.countries.job

import com.nhaarman.mockito_kotlin.isNull
import com.nhaarman.mockito_kotlin.verify
import com.rodrigohenriques.countries.data.FakeData
import com.rodrigohenriques.countries.feature.countries.CountriesState
import io.fluent.StateType
import io.fluent.rx.RxStore
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class SearchCountryByNameJobTest {

  private val scheduler: TestScheduler = TestScheduler()
  private val store = RxStore(CountriesState.initialState())
  private var searchCountryByNameJob: SearchCountryByNameJob

  init {
    searchCountryByNameJob = SearchCountryByNameJob(store, scheduler)
  }

  @Test
  fun bind_withFullName_shouldFilterList() {
    val countries = FakeData.countries()
    val input = "Afghanistan"

    store.update(CountriesState(type = StateType.Success, countryList = countries))

    val stateObserver = store.stateChanges().test()
    val jobObserver = searchCountryByNameJob.bind(input).test()

    scheduler.advanceTimeBy(1, TimeUnit.SECONDS)

    jobObserver.assertComplete()
    stateObserver.assertNotComplete()
    stateObserver.assertValueCount(2)

    val state = store.state()

    assertThat(state.type, `is`(StateType.Success as StateType))
    assertThat(state.countryList, `is`(countries))
    assertThat(state.query, `is`(input))
    assertThat(state.queriedList?.size, `is`(1))
    assertThat(state.queriedList?.first()?.name , `is`("Afghanistan"))
  }

  @Test
  fun bind_withEmptyQuery_shouldNotFilterList() {
    val countries = FakeData.countries()
    val input = ""

    store.update(CountriesState(type = StateType.Success, countryList = countries))

    val stateObserver = store.stateChanges().test()
    val jobObserver = searchCountryByNameJob.bind(input).test()

    scheduler.advanceTimeBy(1, TimeUnit.SECONDS)

    jobObserver.assertComplete()
    stateObserver.assertNotComplete()
    stateObserver.assertValueCount(1)

    val state = store.state()

    assertThat(state.type, `is`(StateType.Success as StateType))
    assertThat(state.countryList, `is`(countries))
    assertThat(state.query, CoreMatchers.nullValue())
    assertThat(state.queriedList, CoreMatchers.nullValue())
  }

  @Test
  fun bind_withPartialNameLowerCase_shouldReturnTwoCountries() {
    val countries = FakeData.countries()
    val input = "al"

    store.update(CountriesState(type = StateType.Success, countryList = countries))

    val stateObserver = store.stateChanges().test()
    val jobObserver = searchCountryByNameJob.bind(input).test()

    scheduler.advanceTimeBy(1, TimeUnit.SECONDS)

    jobObserver.assertComplete()
    stateObserver.assertNotComplete()
    stateObserver.assertValueCount(2)

    val state = store.state()

    assertThat(state.type, `is`(StateType.Success as StateType))
    assertThat(state.countryList, `is`(countries))
    assertThat(state.query, `is`(input))
    assertThat(state.queriedList?.size, `is`(2))
    assertThat(state.queriedList?.map { it.name }, `is`(listOf("Albania", "Algeria")))
  }
}