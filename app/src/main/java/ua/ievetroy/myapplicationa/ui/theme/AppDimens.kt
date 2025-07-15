package ua.ievetroy.myapplicationa.ui.theme

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp

object AppDimens {

    val screenWidthDp: Int
        @Composable get() = LocalConfiguration.current.screenWidthDp

    val screenHeightDp: Int
        @Composable get() = LocalConfiguration.current.screenHeightDp

    object Base {
        val baseDp: Int
            @Composable get() = minOf(screenWidthDp, screenHeightDp)

        val normalizedFactor: Float
            @Composable get() {
                val baseReferenceDp = 800f
                return (screenHeightDp / baseReferenceDp).coerceIn(0.001f, 10f)
            }

        @Composable
        fun scaledDp(baseSize: Float, minDp: Dp, maxDp: Dp): Dp {
            val scaled = baseSize * normalizedFactor
            return scaled.dp.coerceIn(minDp, maxDp)
        }
    }

    object TopBar {
        val paddingTop: Dp
            @Composable get() = Base.scaledDp(27f, 4.dp, 30.dp)

        val paddingSides: Dp
            @Composable get() = Base.scaledDp(screenWidthDp * 0.04f, 7.dp, 28.dp)
    }

    object BottomBar {
        val paddingTop: Dp
            @Composable get() = Base.scaledDp(13f, 1.dp, 20.dp)

        val paddingSides: Dp
            @Composable get() = Base.scaledDp(screenWidthDp * 0.10f, 20.dp, 96.dp)
    }

    object WordCard {
        val paddingSides: Dp
            @Composable get() = Base.scaledDp(screenWidthDp * 0.04f, 8.dp, 32.dp)

        val paddingTop: Dp
            @Composable get() = Base.scaledDp(24f, 5.dp, 24.dp)

        val paddingBottom: Dp
            @Composable get() = Base.scaledDp(25f, 5.dp, 35.dp)

        val cornerRadius: Dp
            @Composable get() = Base.scaledDp(32f, 16.dp, 40.dp)
    }

    object WordItem {
        val paddingSides: Dp
            @Composable get() = Base.scaledDp(screenWidthDp * 0.09f, 8.dp, 20.dp)

        val paddingTopExample: Dp
            @Composable get() = Base.scaledDp(10f, 5.dp, 24.dp)

        val paddingTop: Dp
            @Composable get() = Base.scaledDp(25f, 1.dp, 50.dp)

        val paddingTopListenButton: Dp
            @Composable get() = Base.scaledDp(2f, 0.dp, 10.dp)

        val paddingSidesListenButton: Dp
            @Composable get() = Base.scaledDp(screenWidthDp * 0.07f, 10.dp, 32.dp)

    }

    object ButtonSettings {
        val sizeButton: Dp
            @Composable get() = Base.scaledDp(36f, 25.dp, 37.dp)
    }

    object ContextMenuButton {
        val sizeButton: Dp
            @Composable get() = Base.scaledDp(30f, 20.dp, 40.dp)
    }

    object FlipButton {
        val paddingEnd: Dp
            @Composable get() = Base.scaledDp(12f, 3.dp, 30.dp)

        val paddingTop: Dp
            @Composable get() = Base.scaledDp(12f, 3.dp, 30.dp)

        val sizeButton: Dp
            @Composable get() = Base.scaledDp(36f, 24.dp, 40.dp)
    }

    object HomeButton {
        val sizeIcon: Dp
            @Composable get() = Base.scaledDp(32f, 20.dp, 36.dp)
    }

    object LibraryButton {
        val sizeIcon: Dp
            @Composable get() = Base.scaledDp(32f, 20.dp, 36.dp)
    }

    object ListenWordButton {
        val sizeIcon: Dp
            @Composable get() = Base.scaledDp(28f, 20.dp, 36.dp)
    }

}
