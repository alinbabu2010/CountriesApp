package com.sample.countriesapp.presentation

import com.sample.countriesapp.domain.models.DetailedCountry
import com.sample.countriesapp.domain.models.SimpleCountry

data class CountriesUiState(
    val countries: List<SimpleCountry> = emptyList(),
    val selectedCountry: DetailedCountry? = null,
    val isLoading: Boolean = false
)