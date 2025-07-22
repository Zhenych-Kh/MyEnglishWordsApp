package ua.ievetroy.myapplicationa.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.ui.theme.AppModifiers
import ua.ievetroy.myapplicationa.ui.theme.AppTypography

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Column(
        modifier = AppModifiers.rootColumnModifier
            .padding(top = 16.dp) // ← твій внутрішній паддінг
    ) {
        Text("Налаштування", style = AppTypography.wordTitle())
    }
}

