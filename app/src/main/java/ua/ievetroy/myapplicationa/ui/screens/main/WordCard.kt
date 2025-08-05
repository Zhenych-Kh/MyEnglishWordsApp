package ua.ievetroy.myapplicationa.ui.screens.main

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.ui.components.shadows.uniformShadow
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

    Box(
        modifier = modifier
            .graphicsLayer {
                this.rotationY = rotationY
                this.cameraDistance = cameraDistance
            }
            .uniformShadow(
                alpha = 0.3f,
                cornerRadius = cornerRadius,
                blurRadius = 20.dp,
                yOffset = 9f,
                xOffset = -10f
            )
            .background(Color.White, shape = RoundedCornerShape(cornerRadius))
    ) {
        if (rotationY <= 90f) {
            FrontSide(words = words, onFlip = onFlip, onSwipeLeft = onSwipeLeft)
        } else {
            Box(modifier = Modifier.graphicsLayer { this.rotationY = 180f }) {
                BackSide(onFlip = onFlip)
            }
        }
    }
}
