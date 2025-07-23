package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsItem

@Composable
fun SettingsTheme() {
    var selectedTheme by remember { mutableStateOf("Світла") }
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ThemeBottomSheet(
            selectedTheme = selectedTheme,
            onSelect = {
                selectedTheme = it
                showSheet = false
            },
            onDismiss = { showSheet = false }
        )
    }

    SettingsItem(
        title = "Тема",
        value = selectedTheme,
        onClick = { showSheet = true }
    )
}

