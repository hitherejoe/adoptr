package co.joebirch.adoptr

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class FavoriteState {
    SELECTED, UNSELECTED
}

@Composable
fun DogDetail(
    selectedDog: Dog,
    isFavorite: Boolean,
    navigateUp: () -> Unit,
    toggleFavorite: (dogId: String) -> Unit,
    onAdoptClicked: (url: String) -> Unit
) {
    val scrollState = rememberScrollState()
    Scaffold(
        floatingActionButton = {
            val adoptDescription = stringResource(id = R.string.cd_adopt_me)
            ExtendedFloatingActionButton(
                modifier = Modifier.semantics(mergeDescendants = true) {
                    contentDescription = adoptDescription
                },
                text = {
                    Text(text = stringResource(id = R.string.label_adopt_me))
                },
                icon = {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                },
                onClick = {
                    onAdoptClicked(selectedDog.url)
                })
        },
        content = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxSize()
                        .background(MaterialTheme.colors.surface)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                alpha = kotlin.math.min(1f, 1 - (scrollState.value / 600f))
                                translationY = -scrollState.value * 0.1f
                            },
                        painter = painterResource(selectedDog.image),
                        contentDescription = selectedDog.name,
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = selectedDog.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Attributes(selectedDog)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = selectedDog.description,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                }
                Toolbar(
                    selectedDog.id, scrollState, isFavorite, navigateUp, toggleFavorite
                )
            }
        }
    )
}

@Composable
fun BoxScope.Toolbar(
    dogId: String,
    scrollState: ScrollState,
    isFavorite: Boolean,
    navigateUp: () -> Unit,
    toggleFavorite: (dogId: String) -> Unit
) {
    Box(
        modifier = Modifier
            .alpha(kotlin.math.max(0.5f, scrollState.value / 600f))
            .shadow(elevation = 12.dp)
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .height(56.dp)
            .background(color = MaterialTheme.colors.primary)
    )
    Icon(
        modifier = Modifier
            .padding(16.dp)
            .align(Alignment.TopStart)
            .clickable {
                navigateUp()
            },
        imageVector = Icons.Default.ArrowBack,
        contentDescription = stringResource(id = R.string.cd_back),
        tint = MaterialTheme.colors.onPrimary
    )
    FavoriteIcon(
        dogId = dogId, isFavorite = isFavorite, toggleFavorite = toggleFavorite
    )
}

@Composable
fun BoxScope.FavoriteIcon(
    dogId: String,
    isFavorite: Boolean,
    toggleFavorite: (dogId: String) -> Unit
) {
    val currentPage = remember {
        mutableStateOf(
            if (isFavorite) FavoriteState.SELECTED else FavoriteState.UNSELECTED
        )
    }

    Crossfade(
        targetState = currentPage.value,
        modifier = Modifier.align(Alignment.TopEnd)
    ) { screen ->
        when (screen) {
            FavoriteState.UNSELECTED -> Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        currentPage.value = FavoriteState.SELECTED
                        toggleFavorite(dogId)
                    },
                painter = painterResource(id = R.drawable.ic_baseline_star_outline_24),
                contentDescription = stringResource(id = R.string.cd_favorite),
                tint = MaterialTheme.colors.onPrimary
            )
            FavoriteState.SELECTED -> Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        currentPage.value = FavoriteState.UNSELECTED
                        toggleFavorite(dogId)
                    },
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(id = R.string.cd_unfavorite),
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun Attributes(dog: Dog) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "🐶 " + dog.breed)
            Text(text = "🎂 " + dog.age)
            Text(text = "⚥ " + dog.gender)
        }
    }
}
