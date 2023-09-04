package com.sample.countriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.countriesapp.presentation.CountriesScreen
import com.sample.countriesapp.presentation.CountriesViewModel
import com.sample.countriesapp.ui.theme.CountriesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesAppTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                CountriesScreen(
                    state = state,
                    onSelectCountry = viewModel::selectCountry,
                    onDismissDialog = viewModel::dismissCountryDialog
                )
            }
        }
    }
}