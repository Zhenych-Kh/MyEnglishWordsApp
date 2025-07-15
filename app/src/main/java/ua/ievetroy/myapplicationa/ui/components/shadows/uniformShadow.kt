package ua.ievetroy.myapplicationa.ui.components.shadows

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.uniformShadow(
    color: Color = Color.Black,
    alpha: Float = 0.4f,
    cornerRadius: Dp = 33.dp,
    blurRadius: Dp = 20.dp,
    yOffset: Float = 6f,
    xOffset: Float = -10f
): Modifier = this.drawBehind {
    val paint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        this.color = color.copy(alpha = alpha).toArgb()
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