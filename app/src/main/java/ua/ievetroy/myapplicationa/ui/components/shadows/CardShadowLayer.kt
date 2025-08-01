package ua.ievetroy.myapplicationa.ui.components.shadows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CardShadowLayer(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 33.dp,
    blurRadius: Dp = 24.dp,
    yOffset: Float = 9f,
    xOffset: Float = -10f,
    alpha: Float = 0.3f
) {
    Box(
        modifier = modifier
            .size(width = 300.dp, height = 420.dp) // ← твій розмір картки!
            .drawBehind {
                val paint = Paint().asFrameworkPaint().apply {
                    isAntiAlias = true
                    color = Color.Black.copy(alpha = alpha).toArgb()
                    maskFilter = android.graphics.BlurMaskFilter(
                        blurRadius.toPx(),
                        android.graphics.BlurMaskFilter.Blur.NORMAL
                    )
                }
                drawContext.canvas.nativeCanvas.drawRoundRect(
                    xOffset,
                    yOffset,
                    size.width,
                    size.height,
                    cornerRadius.toPx(),
                    cornerRadius.toPx(),
                    paint
                )
            }
    )
}
