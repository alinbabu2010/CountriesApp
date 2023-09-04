package com.sample.countriesapp.data.repository

import com.apollographql.apollo3.ApolloClient
import com.sample.CountriesQuery
import com.sample.CountryQuery
import com.sample.countriesapp.data.mappers.toDetailedCountry
import com.sample.countriesapp.data.mappers.toSimpleCountry
import com.sample.countriesapp.domain.models.DetailedCountry
import com.sample.countriesapp.domain.models.SimpleCountry
import com.sample.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject

class ApolloCountryRepository @Inject constructor(
    private val apolloClient: ApolloClient
) : CountryRepository {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data?.countries?.map { it.toSimpleCountry() } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data?.country?.toDetailedCountry()
    }

}