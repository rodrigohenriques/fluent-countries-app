package com.rodrigohenriques.countries.data.valueobjects

import android.os.Parcel
import android.os.Parcelable

data class Language(
    val iso639_1: String,
    val iso639_2: String,
    val name: String,
    val nativeName: String
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readString()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(iso639_1)
    parcel.writeString(iso639_2)
    parcel.writeString(name)
    parcel.writeString(nativeName)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Language> {
    override fun createFromParcel(parcel: Parcel): Language {
      return Language(parcel)
    }

    override fun newArray(size: Int): Array<Language?> {
      return arrayOfNulls(size)
    }
  }
}