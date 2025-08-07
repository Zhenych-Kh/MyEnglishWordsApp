package ua.ievetroy.myapplicationa

import AppNavHost
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ua.ievetroy.myapplicationa.data.preferences.settings.SettingsRepository
import ua.ievetroy.myapplicationa.ui.LocaleHelper
import ua.ievetroy.myapplicationa.ui.screens.main.MainScreen

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppNavHost(repository = repository)
        }
    }
}


