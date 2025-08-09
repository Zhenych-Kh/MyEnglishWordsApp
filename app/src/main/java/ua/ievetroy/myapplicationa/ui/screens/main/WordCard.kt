package ua.ievetroy.myapplicationa.ui.screens.main

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.ui.components.shadows.ShadowParams
import ua.ievetroy.myapplicationa.ui.components.shadows.themedShadow
import ua.ievetroy.myapplicationa.ui.screens.main.components.BackSide
import ua.ievetroy.myapplicationa.ui.screens.main.components.FrontSide
import ua.ievetroy.myapplicationa.ui.theme.AppDimens

@Composable
fun WordCard(
    words: List<Word>,
    isFlipped: Boolean,
    onFlip: () -> Unit,
    onSwipeLeft: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(600),
        label = "flip"
    )

    val cameraDistance = 12 * LocalDensity.current.density
    val cornerRadius = AppDimens.WordCard.cornerRadius

    val isDark = isSystemInDarkTheme()

    Box(
        modifier = modifier
            .graphicsLayer {
                this.rotationY = rotationY
                this.cameraDistance = cameraDistance
            }
            .themedShadow(
                light = ShadowParams(
                    alpha = 0.30f,
                    cornerRadius = cornerRadius,
                    blurRadius = 20.dp,
                    yOffset = 0f,
                    xOffset = 0f,
                    color = Color.Black
                ),
                dark = ShadowParams(
                    alpha = 0.5f,
                    cornerRadius = cornerRadius,
                    blurRadius = 7.dp,
                    yOffset = 0f,
                    xOffset = 0f,
                    color = Color.White
                )
            )
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(cornerRadius))
    ) {
        DebugThemeFlag()
        if (rotationY <= 90f) {
            FrontSide(words = words, onFlip = onFlip, onSwipeLeft = onSwipeLeft)
        } else {
            Box(modifier = Modifier.graphicsLayer { this.rotationY = 180f }) {
                BackSide(onFlip = onFlip)
            }
        }
    }


}
@Composable
fun DebugThemeFlag() {
    val isDark = isSystemInDarkTheme()
    SideEffect {
        android.util.Log.d("Theme", "isDark = $isDark")
    }
}


