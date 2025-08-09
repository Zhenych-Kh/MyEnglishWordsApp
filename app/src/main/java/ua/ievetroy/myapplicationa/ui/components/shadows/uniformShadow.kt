package ua.ievetroy.myapplicationa.ui.components.shadows

import android.graphics.BlurMaskFilter
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.ui.theme.LocalIsAppDark


// shadow utils
data class ShadowParams(
    val alpha: Float,
    val cornerRadius: Dp,
    val blurRadius: Dp,
    val yOffset: Float,
    val xOffset: Float,
    val color: Color
)

@Composable
fun Modifier.themedShadow(
    light: ShadowParams,
    dark: ShadowParams
): Modifier {
    val isDark = LocalIsAppDark.current
    val p = if (isDark) dark else light
    return this.drawBehind {
        val paint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            color = p.color.copy(alpha = p.alpha).toArgb()
            maskFilter = android.graphics.BlurMaskFilter(
                p.blurRadius.toPx(),
                android.graphics.BlurMaskFilter.Blur.NORMAL
            )
        }
        drawContext.canvas.nativeCanvas.drawRoundRect(
            p.xOffset, p.yOffset, size.width, size.height,
            p.cornerRadius.toPx(), p.cornerRadius.toPx(), paint
        )
    }
}


