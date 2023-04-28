package com.example.moviesvapp.ui.components.favorite

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesvapp.R
import com.example.moviesvapp.model.database.FavoriteMovie
import com.example.moviesvapp.ui.theme.MyApplicationTheme
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(favoriteMoviesFlow: StateFlow<List<FavoriteMovie>>) {
    val favoriteMovies by favoriteMoviesFlow.collectAsState(initial = emptyList())

    MyApplicationTheme {
        val pagerState = rememberPagerState()
        val fling = PagerDefaults.flingBehavior(
            state = pagerState,
            pagerSnapDistance = PagerSnapDistance.atMost(4)
        )

        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val cardWidth = screenWidth * 0.7f
        val cardHeight = screenHeight * 0.7f
        val padding = (screenWidth - cardWidth) / 2

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                pageCount = favoriteMovies.size,
                state = pagerState,
                flingBehavior = fling,
                contentPadding = PaddingValues(start = padding, end = padding)
            ) { page ->
                val favoriteMovie = favoriteMovies[page]
                FavoriteMovieItem(
                    favoriteMovie = favoriteMovie,
                    pagerState = pagerState,
                    currentPage = page,
                    cardWidth = cardWidth,
                    cardHeight = cardHeight
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMovieItem(
    favoriteMovie: FavoriteMovie,
    pagerState: PagerState,
    currentPage: Int,
    cardWidth: Dp,
    cardHeight: Dp
) {
    val painter = rememberAsyncImagePainter(model = favoriteMovie.poster)
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    val pageOffset = (
            (pagerState.currentPage - currentPage) + pagerState.currentPageOffsetFraction
            ).absoluteValue

    val scale = lerp(
        start = 0.8f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )

    Card(
        modifier = Modifier
            .size(cardWidth, cardHeight)
            .scale(scale)
            .animateContentSize()
            .clickable(onClick = {
            openBottomSheet = !openBottomSheet
        }),
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
    if (openBottomSheet) {
        val sheetState = rememberModalBottomSheetState()
        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            content = {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = favoriteMovie.poster),
                        contentDescription = stringResource(R.string.movie_poster),
                        modifier = Modifier.height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.category),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        favoriteMovie.type,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.release_date),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        favoriteMovie.year,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        )
    }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}