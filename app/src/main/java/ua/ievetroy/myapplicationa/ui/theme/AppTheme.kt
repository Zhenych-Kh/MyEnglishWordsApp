package ua.ievetroy.myapplicationa.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.ievetroy.myapplicationa.ui.screens.main.util.SetStatusBarColors

@Composable
fun AppTheme(
    darkTheme: Boolean, // ← передавай з Settings (light/dark/system), не з isSystemInDarkTheme()
    content: @Composable () -> Unit
) {
    val lightScheme = lightColorScheme(
        background  = AppColors.AppBackgraundsTheme.BackgraundsLight,
        surface     = AppColors.AppBackgraundsTheme.BackgraundsLightsettings,
        onBackground= AppColors.AppBackgraundsTheme.TextColorWordCard,
        onSurface   = AppColors.AppBackgraundsTheme.TextColorWordCard,
        primary     = AppColors.AppBackgraundsTheme.TextColorButton,
        onPrimary   = Color.White
    )

    val darkScheme = darkColorScheme(
        background  = AppColors.AppDarkBackgraundsTheme.BackgraundsLight,
        surface     = AppColors.AppDarkBackgraundsTheme.BackgraundsLightsettings,
        onBackground= AppColors.AppDarkBackgraundsTheme.TextColorWordCard,
        onSurface   = AppColors.AppDarkBackgraundsTheme.TextColorWordCard,
        primary     = AppColors.AppDarkBackgraundsTheme.TextColorButton,
        onPrimary   = Color.Black
    )

    val scheme = if (darkTheme) darkScheme else lightScheme

    CompositionLocalProvider(LocalIsAppDark provides darkTheme) {
        MaterialTheme(colorScheme = scheme) {
            // статус-бар синхронізуємо в межах ТІЄЇ Ж композиції
            val systemUi = rememberSystemUiController()
            val bg = scheme.background
            val darkIcons = bg.luminance() > 0.5f
            SideEffect { systemUi.setStatusBarColor(bg, darkIcons) }

            content()
        }
    }
}

