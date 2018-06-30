package com.rodrigohenriques.countries.data.valueobjects

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
    private val latlng: List<Int>,
    val demonym: String,
    val area: Long,
    val gini: Double,
    val timezones: List<String>,
    val borders: List<String>,
    val nativeName: String,
    val numericCode: String,
    val currencies: List<Currency>,
    val languages: List<Language>,
    val translations: Map<String, String>,
    val regionalBlocs: List<RegionalBlock>
)
