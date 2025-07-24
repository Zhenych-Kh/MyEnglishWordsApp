package ua.ievetroy.myapplicationa

import AppNavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ua.ievetroy.myapplicationa.data.preferences.settings.SettingsRepository
import ua.ievetroy.myapplicationa.ui.screens.main.MainScreen

class MainActivity : ComponentActivity() {
    private val repository by lazy { SettingsRepository(this) } // this — Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            AppNavHost(repository = repository)
        }
    }
}
