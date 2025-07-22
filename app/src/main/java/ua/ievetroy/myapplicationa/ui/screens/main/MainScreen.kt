package ua.ievetroy.myapplicationa.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.ievetroy.myapplicationa.ui.screens.main.bars.BottomBar
import ua.ievetroy.myapplicationa.ui.screens.main.bars.TopBar
import ua.ievetroy.myapplicationa.ui.theme.AppColors
import ua.ievetroy.myapplicationa.ui.theme.AppModifiers

@Composable
fun SetStatusBarColors(isDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = if (isDarkTheme) Color.Black else AppColors.AppBackgraundsTheme.BackgraundsLight,
            darkIcons = !isDarkTheme
        )
    }
}

@Composable
fun MainScreen(
    onSettingsClick: () -> Unit
) {
    var isFlipped by remember { mutableStateOf(false) }
    SetStatusBarColors(isDarkTheme = false)

    Column(
        modifier = AppModifiers.rootColumnModifier
    ) {
        TopBar(
            modifier = AppModifiers.topBarModifier,
            onFlip = { isFlipped = !isFlipped },
            onSettingsClick = onSettingsClick // ← додано
        )
        WordCard(
            isFlipped = isFlipped,
            onFlip = { isFlipped = !isFlipped },
            modifier = Modifier
                .weight(1f)
                .then(AppModifiers.wordCardModifier)
        )
        BottomBar(
            AppModifiers.bottomBarModifier
        )
    }
}