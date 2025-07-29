package ua.ievetroy.myapplicationa.ui.screens.main.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.ievetroy.myapplicationa.ui.theme.AppColors

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
