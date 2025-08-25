package ua.ievetroy.myapplicationa

import AppNavHost
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ua.ievetroy.myapplicationa.data.preferences.settings.SettingsRepository
import ua.ievetroy.myapplicationa.ui.LocaleHelper
import ua.ievetroy.myapplicationa.ui.screens.main.MainScreen
import ua.ievetroy.myapplicationa.ui.theme.AppTheme
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModel
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModelFactory

class MainActivity : ComponentActivity() {

    private val repository by lazy { SettingsRepository(this) }

    override fun attachBaseContext(newBase: Context) {
        // Отримуємо обрану мову з SharedPreferences (збережену при виборі)
        val prefs = newBase.getSharedPreferences("settings", Context.MODE_PRIVATE)
        val langCode = prefs.getString("language_code", "uk") ?: "uk"

        // Змінюємо контекст на вибрану мову
        val context = LocaleHelper.updateLocale(newBase, langCode)
        super.attachBaseContext(context)
    }

    private val settingsRepository by lazy { SettingsRepository(applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val settingsVm: SettingsViewModel = viewModel(
                factory = SettingsViewModelFactory(repository)
            )
            val themeCode by settingsVm.selectedTheme.collectAsState()

            val isDark = when (themeCode) {
                "dark" -> true
                "light" -> false
                else -> isSystemInDarkTheme()
            }

            AppTheme(darkTheme = isDark) {
                AppRoot(repository = settingsRepository)
            }
        }


    }
}


