package com.rodrigohenriques.countries.data.valueobjects

import android.os.Parcel
import android.os.Parcelable

data class Country(
    val name: String,
    val topLevelDomain: List<String>,
    val alpha2Code: String,
    val alpha3Code: String,
    val callingCodes: List<String>,
    val capital: String,
    val altSpellings: List<String>,
    val region: String,
    val subregion: String,
    val population: String,
    private val latlng: List<Double>,
    val demonym: String,
    val area: Double,
    val gini: Double,
    val timezones: List<String>,
    val borders: List<String>,
    val nativeName: String,
    val numericCode: String,
    val currencies: List<Currency>,
    val languages: List<Language>,
    val translations: Map<String, String>,
    val regionalBlocs: List<RegionalBlock>
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.createStringArrayList(),
      parcel.readString(),
      parcel.readString(),
      parcel.createStringArrayList(),
      parcel.readString(),
      parcel.createStringArrayList(),
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.createDoubleArray().asList(),
      parcel.readString(),
      parcel.readDouble(),
      parcel.readDouble(),
      parcel.createStringArrayList(),
      parcel.createStringArrayList(),
      parcel.readString(),
      parcel.readString(),
      parcel.createTypedArrayList(Currency),
      parcel.createTypedArrayList(Language),
      emptyMap(),
      parcel.createTypedArrayList(RegionalBlock)) {
  }

  fun location(): Pair<Double, Double> = latlng[0] to latlng[1]

  fun flagUrl() = "http://www.geonames.org/flags/x/$alpha2Code.gif".toLowerCase()
  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(name)
    parcel.writeStringList(topLevelDomain)
    parcel.writeString(alpha2Code)
    parcel.writeString(alpha3Code)
    parcel.writeStringList(callingCodes)
    parcel.writeString(capital)
    parcel.writeStringList(altSpellings)
    parcel.writeString(region)
    parcel.writeString(subregion)
    parcel.writeString(population)
    parcel.writeDoubleArray(latlng.toDoubleArray())
    parcel.writeString(demonym)
    parcel.writeDouble(area)
    parcel.writeDouble(gini)
    parcel.writeStringList(timezones)
    parcel.writeStringList(borders)
    parcel.writeString(nativeName)
    parcel.writeString(numericCode)
    parcel.writeTypedList(currencies)
    parcel.writeTypedList(languages)
    parcel.writeTypedList(regionalBlocs)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Country> {
    override fun createFromParcel(parcel: Parcel): Country {
      return Country(parcel)
    }

    override fun newArray(size: Int): Array<Country?> {
      return arrayOfNulls(size)
    }
  }
}

