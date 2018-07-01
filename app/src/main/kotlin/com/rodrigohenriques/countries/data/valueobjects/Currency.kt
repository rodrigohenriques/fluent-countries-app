package com.rodrigohenriques.countries.data.valueobjects

import android.os.Parcel
import android.os.Parcelable

data class Currency(
    val code: String,
    val name: String,
    val symbol: String,
    val cioc: String?
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readString())

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(code)
    parcel.writeString(name)
    parcel.writeString(symbol)
    parcel.writeString(cioc)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Currency> {
    override fun createFromParcel(parcel: Parcel): Currency {
      return Currency(parcel)
    }

    override fun newArray(size: Int): Array<Currency?> {
      return arrayOfNulls(size)
    }
  }
}