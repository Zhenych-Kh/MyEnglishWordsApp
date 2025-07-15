package ua.ievetroy.myapplicationa.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.ievetroy.myapplicationa.ui.components.words.WordCard
import ua.ievetroy.myapplicationa.ui.components.bars.BottomBar
import ua.ievetroy.myapplicationa.ui.components.bars.TopBar
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

@Preview
@Composable
fun MainScreen() {
    SetStatusBarColors(isDarkTheme = false)
    Column(
        modifier = AppModifiers.rootColumnModifier
    ) {
        TopBar(AppModifiers.topBarModifier)
        WordCard(
            modifier = Modifier
                .weight(1f)
                .then(AppModifiers.wordCardModifier)
        )
        BottomBar(AppModifiers.bottomBarModifier)
    }
}
