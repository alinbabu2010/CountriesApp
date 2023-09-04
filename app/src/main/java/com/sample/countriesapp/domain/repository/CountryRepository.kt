package com.sample.countriesapp.domain.repository

import com.sample.countriesapp.domain.models.DetailedCountry
import com.sample.countriesapp.domain.models.SimpleCountry

interface CountryRepository {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}