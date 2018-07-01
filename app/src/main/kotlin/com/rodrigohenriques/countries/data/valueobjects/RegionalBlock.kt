package com.rodrigohenriques.countries.data.valueobjects

import android.os.Parcel
import android.os.Parcelable

data class RegionalBlock(
    val acronym: String,
    val name: String,
    val otherAcronyms: List<String>,
    val otherNames: List<String>
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.createStringArrayList(),
      parcel.createStringArrayList()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(acronym)
    parcel.writeString(name)
    parcel.writeStringList(otherAcronyms)
    parcel.writeStringList(otherNames)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<RegionalBlock> {
    override fun createFromParcel(parcel: Parcel): RegionalBlock {
      return RegionalBlock(parcel)
    }

    override fun newArray(size: Int): Array<RegionalBlock?> {
      return arrayOfNulls(size)
    }
  }
}