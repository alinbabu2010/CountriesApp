package com.sample.countriesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sample.countriesapp.domain.models.DetailedCountry
import com.sample.countriesapp.domain.models.SimpleCountry

@Composable
fun CountriesScreen(
    state: CountriesUiState,
    onSelectCountry: (code: String) -> Unit,
    onDismissDialog: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(state.countries) { country ->
                    CountryItem(
                        country = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectCountry(country.code) }
                    )
                    Divider(Modifier.padding(horizontal = 16.dp))
                }
            }

            if (state.selectedCountry != null) {
                CountryDialog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .padding(16.dp),
                    country = state.selectedCountry,
                    onDismiss = onDismissDialog
                )
            }
        }
    }

}

@Composable
fun CountryItem(country: SimpleCountry, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = country.emoji, fontSize = 30.sp)
        Column(modifier = Modifier.weight(1F)) {
            Text(text = country.name, fontSize = 24.sp)
            Text(text = country.capital)
        }
    }
}

@Composable
fun CountryDialog(
    country: DetailedCountry,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {

    val languages = remember(country.languages) {
        country.languages.joinToString(",")
    }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = country.emoji, fontSize = 64.sp)
            Text(
                text = country.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Continent: ${country.continent}", textAlign = TextAlign.Center)
            Text(text = "Capital: ${country.capital}", textAlign = TextAlign.Center)
            Text(text = "Currency: ${country.currency}", textAlign = TextAlign.Center)
            Text(text = "Language: $languages", textAlign = TextAlign.Center)
        }
    }

}