package ua.ievetroy.myapplicationa.ui.components.words

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity

@Composable
fun FlipCard(
    isFlipped: Boolean,
    modifier: Modifier = Modifier,
    front: @Composable () -> Unit,
    back: @Composable () -> Unit
) {
    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween (durationMillis = 500),
        label = "flip"
    )

    val cameraDistance = 12 * LocalDensity.current.density

    Box(
        modifier = modifier
            .graphicsLayer {
                this.rotationY = rotationY
                this.cameraDistance = cameraDistance
            }
    ) {
        if (rotationY <= 90f) {
            front()
        } else {
            Box(
                modifier = Modifier.graphicsLayer {
                    this.rotationY = 180f
                }
            ) {
                back()
            }
        }
    }
}