package co.joebirch.adoptr.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import co.joebirch.adoptr.R

@Composable
fun lightTheme() = lightColors(
    primary = colorResource(id = R.color.blue),
    primaryVariant = colorResource(id = R.color.blue_dark),
    onPrimary = colorResource(id = R.color.white),
    secondary = colorResource(id = R.color.teal),
    onSecondary = colorResource(id = R.color.white),
    surface = colorResource(id = R.color.white),
    onSurface = colorResource(id = R.color.black),
    background = colorResource(id = R.color.white),
    onBackground = colorResource(id = R.color.black)
)

@Composable
fun darkTheme() = darkColors(
    primary = colorResource(id = R.color.black),
    primaryVariant = colorResource(id = R.color.dark_primary_variant),
    onPrimary = colorResource(id = R.color.dark_color_on_primary),
    secondary = colorResource(id = R.color.blue),
    onSecondary = colorResource(id = R.color.dark_color_on_secondary),
    surface = colorResource(id = R.color.dark_color_surface),
    onSurface = colorResource(id = R.color.dark_color_on_surface),
    background = colorResource(id = R.color.dark_color_background),
    onBackground = colorResource(id = R.color.dark_color_on_background)
)

@Composable
fun PuppyParkTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        darkTheme()
    } else {
        lightTheme()
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}