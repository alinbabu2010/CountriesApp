package com.sample.countriesapp.domain.usecases

import com.sample.countriesapp.domain.models.DetailedCountry
import com.sample.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val countryRepository: CountryRepository
) {

    suspend operator fun invoke(code: String): DetailedCountry? {
        return countryRepository.getCountry(code)
    }

}