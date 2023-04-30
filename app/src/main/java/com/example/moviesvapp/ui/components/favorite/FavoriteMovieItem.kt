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
import com.example.moviesvapp.ui.components.detail.MovieDetails
import com.example.moviesvapp.ui.components.favorite.ScaleConstants.INITIAL_FRACTION_VALUE
import com.example.moviesvapp.ui.components.favorite.ScaleConstants.LERP_START_WEIGHT
import com.example.moviesvapp.ui.components.favorite.ScaleConstants.PAGE_OFFSET_LOWER_BOUND
import com.example.moviesvapp.ui.components.favorite.ScaleConstants.PAGE_OFFSET_UPPER_BOUND
import com.example.moviesvapp.ui.components.favorite.ScaleConstants.SCALE_START
import com.example.moviesvapp.ui.components.favorite.ScaleConstants.SCALE_STOP
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
        start = SCALE_START,
        stop = SCALE_STOP,
        fraction = INITIAL_FRACTION_VALUE - pageOffset.coerceIn(
            PAGE_OFFSET_LOWER_BOUND,
            PAGE_OFFSET_UPPER_BOUND
        )
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
    return (LERP_START_WEIGHT - fraction) * start + fraction * stop
}

object ScaleConstants {
    const val SCALE_START = 0.8f
    const val SCALE_STOP = 1f
    const val PAGE_OFFSET_LOWER_BOUND = 0f
    const val PAGE_OFFSET_UPPER_BOUND = 1f
    const val INITIAL_FRACTION_VALUE = 1f
    const val LERP_START_WEIGHT = 1
}
