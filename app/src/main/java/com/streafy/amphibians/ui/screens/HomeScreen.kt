package com.streafy.amphibians.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.streafy.amphibians.network.Amphibian
import com.streafy.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(state: AmphibianUiState, modifier: Modifier = Modifier) {
    when (state) {
        is AmphibianUiState.Success -> ContentScreen(state.amphibians)
        is AmphibianUiState.Loading -> LoadingScreen()
        is AmphibianUiState.Error -> ErrorScreen()
    }
}

@Composable
fun ContentScreen(amphibians: List<Amphibian>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(items = amphibians, key = { amphibian -> amphibian.name }) { amphibian ->
            AmphibianCard(amphibian = amphibian)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Column {
        Text(text = "${amphibian.name} (${amphibian.type})")
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(amphibian.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = "Amphibian photo"
        )
        Text(text = amphibian.description)
    }
}

@Preview(showBackground = true)
@Composable
fun ContentScreenPreview() {
    AmphibiansTheme {
        val mockData = List(10) { 
            Amphibian("Amphibian $it", "type", "description", "") 
        }
        ContentScreen(amphibians = mockData)
    }
}

@Composable
fun LoadingScreen() {
    Box {
        Text(text = "loading")
    }
}

@Composable
fun ErrorScreen() {
    Box {
        Text(text = "error")
    }
}