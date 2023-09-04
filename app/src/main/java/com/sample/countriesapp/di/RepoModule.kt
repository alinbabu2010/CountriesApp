package com.sample.countriesapp.di

import com.sample.countriesapp.data.repository.ApolloCountryRepository
import com.sample.countriesapp.domain.repository.CountryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    @Singleton
    fun bindCountryRepository(
        apolloCountryRepository: ApolloCountryRepository
    ): CountryRepository

}