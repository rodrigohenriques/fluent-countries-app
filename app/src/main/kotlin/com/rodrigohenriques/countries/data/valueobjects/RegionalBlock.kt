package com.rodrigohenriques.countries.data.valueobjects

data class RegionalBlock(
    val acronym: String,
    val name: String,
    val otherAcronyms: List<String>,
    val otherNames: List<String>
)