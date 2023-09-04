package com.sample.countriesapp.domain.usecases

import com.sample.countriesapp.domain.models.SimpleCountry
import com.sample.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {

    suspend operator fun invoke(): List<SimpleCountry> {
        return countryRepository
            .getCountries()
            .sortedBy { it.name }
    }

}