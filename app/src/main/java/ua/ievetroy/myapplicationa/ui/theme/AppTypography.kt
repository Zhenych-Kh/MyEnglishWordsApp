package ua.ievetroy.myapplicationa.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalDensity
import ua.ievetroy.myapplicationa.ui.theme.AppColors

object AppTypography {

    private val screenWidthDp: Int
        @Composable get() = LocalConfiguration.current.screenWidthDp

    private val screenHeightDp: Int
        @Composable get() = LocalConfiguration.current.screenHeightDp

    val baseDp: Int
        @Composable get() = minOf(screenWidthDp, screenHeightDp)

    private val normalizedFactor: Float
        @Composable get() {
            val baseReferenceDp = 800f
            return (screenHeightDp / baseReferenceDp).coerceIn(0.5f, 1.4f)
        }

    /**
     * Масштабування шрифтів, яке **не враховує** system font scale
     */
    @Composable
    fun scaledSpFixed(baseSize: Float, min: Float, max: Float): TextUnit {
        val configuration = LocalConfiguration.current
        val density = LocalDensity.current

        val fontScale = configuration.fontScale
        val scaled = baseSize * normalizedFactor

        // Конвертуємо в px вручну, потім в sp без масштабування
        val px = with(density) { scaled.dp.toPx() }
        val spRaw = px / fontScale / density.density
        return spRaw.coerceIn(min, max).sp
    }



    val labelSmallFontSize: TextUnit
        @Composable get() = scaledSpFixed(15f, 13f, 20f)

    val wordTitleFontSize: TextUnit
        @Composable get() = scaledSpFixed(27f, 23f, 50f)

    val wordTranslationFontSize: TextUnit
        @Composable get() = scaledSpFixed(22f, 20f, 35f)

    val exampleSentenceFontSize: TextUnit
        @Composable get() = scaledSpFixed(17f, 17f, 27f)

    @Composable
    fun labelSmall() = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = labelSmallFontSize
    )

    @Composable
    fun wordTitle() = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = wordTitleFontSize,
        color = AppColors.AppBackgraundsTheme.TextColorWordCard
    )

    @Composable
    fun wordTranslation() = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = wordTranslationFontSize,
        color = AppColors.AppBackgraundsTheme.TextColorWordCard
    )

    @Composable
    fun exampleSentence() = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = exampleSentenceFontSize,
        color = AppColors.AppBackgraundsTheme.TextColorWordCard
    )
}
