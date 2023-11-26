package com.streafy.amphibians.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.streafy.amphibians.R
import com.streafy.amphibians.network.Amphibian
import com.streafy.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(state: AmphibiansUiState, modifier: Modifier = Modifier) {
    when (state) {
        is AmphibiansUiState.Success -> ContentScreen(state.amphibians)
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Error -> ErrorScreen()
    }
}

@Composable
fun ContentScreen(amphibians: List<Amphibian>, modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(items = amphibians, key = { amphibian -> amphibian.name }) { amphibian ->
            AmphibianCard(amphibian = amphibian)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontWeight = FontWeight.Bold,
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = "Amphibian photo",
                placeholder = painterResource(id = R.drawable.loading_img),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = amphibian.description,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentScreenPreview() {
    AmphibiansTheme {
        val mockData = List(10) { 
            Amphibian("Amphibian $it", "type", "description".repeat(10), "")
        }
        ContentScreen(amphibians = mockData)
    }
}

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(text = "loading...")
    }
}

@Composable
fun ErrorScreen() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(text = "error")
    }
}