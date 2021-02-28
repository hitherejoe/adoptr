package co.joebirch.adoptr

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import co.joebirch.adoptr.theme.PuppyParkTheme
import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: DogViewModel by viewModels()

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuppyParkTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "dogs") {
                    composable("dogs") {
                        viewModel.liveData.observeAsState().value?.let { state ->
                            DogDashboard(state = state, onDogSelected = {
                                navController.navigate("dogs/${it}")
                            }, filterDogs = {
                                viewModel.filterDogsBy(it)
                            })
                        }
                    }
                    composable("dogs/{id}") { backStackEntry ->
                        val puppyName = backStackEntry.arguments?.getString("id")
                        val selectedDog = viewModel.dogs().find { it.id == puppyName }!!
                        DogDetail(
                            selectedDog = selectedDog,
                            isFavorite = viewModel.isDogFavorite(selectedDog.id),
                            navigateUp = { navController.popBackStack() },
                            toggleFavorite = { viewModel.toggleFavorite(it) }
                        ) {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun DogDashboard(
    state: DogListState,
    onDogSelected: (id: String) -> Unit,
    filterDogs: (attribute: DogAttributes) -> Unit
) {
    val scaffoldState = rememberBackdropScaffoldState(
        initialValue = BackdropValue.Concealed
    )
    val coroutineScope = rememberCoroutineScope()
    BackdropScaffold(
        scaffoldState = scaffoldState,
        stickyFrontLayer = false,
        appBar = {
            Toolbar {
                coroutineScope.launch {
                    if (scaffoldState.isRevealed) {
                        scaffoldState.conceal()
                    } else {
                        scaffoldState.reveal()
                    }
                }
            }
        },
        backLayerContent = {
            FilterOptions(attributes = state.includeAttributes) {
                filterDogs(it)
            }
        }, frontLayerContent = {
            DogGrid(dogs = state.dogs) {
                onDogSelected(it)
            }
        })
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.label_empty),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Toolbar(handleFilterClick: () -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
                contentDescription = stringResource(id = R.string.filter_dogs),
                modifier = Modifier
                    .clickable {
                        handleFilterClick()
                    }
                    .padding(16.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun DogGrid(
    dogs: List<Dog>,
    onDogSelected: (id: String) -> Unit
) {
    Spacer(modifier = Modifier.height(16.dp))
    if (dogs.isEmpty()) {
        EmptyState()
    } else {
        Column {
            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                cells = GridCells.Adaptive(200.dp),
                content = {
                    dogs.forEach {
                        item {
                            DogCard(
                                it
                            ) {
                                onDogSelected(it)
                            }
                        }
                    }
                })
        }
    }
}

@Composable
fun FilterOptions(
    attributes: List<DogAttributes>,
    filterDogsBy: (attribute: DogAttributes) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.filter_message),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(36.dp),
            textAlign = TextAlign.Center
        )
        DogAttributes.values().forEach {
            DogAttributeSelector(
                it,
                attributes.contains(it)
            ) {
                filterDogsBy(it)
            }
        }
    }
}

@Composable
fun DogCard(dog: Dog, onDogSelected: (id: String) -> Unit) {
    Row(modifier = Modifier.padding(16.dp)) {
        Card(
            modifier = Modifier.clickable {
                onDogSelected(dog.id)
            }
        ) {
            Column {
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth(),
                        painter = painterResource(dog.image),
                        contentDescription = dog.name,
                        contentScale = ContentScale.Crop
                    )
                    if (dog.reserved) {
                        val reservedDescription = stringResource(id = R.string.cd_reserved)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = SolidColor(MaterialTheme.colors.onSurface),
                                    alpha = 0.6f
                                )
                                .padding(16.dp)
                                .semantics(mergeDescendants = true) {
                                    this.contentDescription = reservedDescription
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null,
                                tint = MaterialTheme.colors.surface
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(id = R.string.label_reserved),
                                color = MaterialTheme.colors.surface
                            )
                        }
                    }
                }
                Text(
                    text = dog.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = dog.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp, bottom = 16.dp
                    )
                )
            }
        }
    }
}

@Composable
fun DogAttributeSelector(
    attribute: DogAttributes,
    isSelected: Boolean = false,
    onSelectionChanged: (DogAttributes) -> Unit
) {
    Text(
        text = attribute.label,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .padding(16.dp)
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(attribute)
                }
            )
            .alpha(if (isSelected) 1f else 0.4f)
    )
}
