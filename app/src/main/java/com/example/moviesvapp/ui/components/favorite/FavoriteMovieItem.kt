package com.example.moviesvapp.ui.components.favorite

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesvapp.R
import com.example.moviesvapp.model.database.FavoriteMovie
import com.example.moviesvapp.ui.components.MovieDetails
import kotlin.math.absoluteValue

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
                MovieDetails(
                    poster = favoriteMovie.poster,
                    type = favoriteMovie.type,
                    year = favoriteMovie.year
                )
            }
        )
    }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}