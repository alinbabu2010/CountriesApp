package com.sample.countriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.countriesapp.domain.usecases.GetCountriesUseCase
import com.sample.countriesapp.domain.usecases.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(CountriesUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            _uiState.update {
                it.copy(
                    countries = withContext(Dispatchers.IO) {
                        getCountriesUseCase()
                    },
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(selectedCountry = getCountryUseCase(code))
            }
        }
    }

    fun dismissCountryDialog() {
        _uiState.update {
            it.copy(selectedCountry = null)
        }
    }

}