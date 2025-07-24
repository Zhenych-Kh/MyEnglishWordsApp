package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsItem

@Composable
fun SettingsLanguage(
    selectedLanguage: String,
    onSelect: (String) -> Unit)
{

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        LanguageBottomSheet(
            selectedLanguage = selectedLanguage,
            onSelect = {
                onSelect(it)
                showSheet = false
            },
            onDismiss = { showSheet = false }
        )
    }

    SettingsItem(
        title = "Мова інтерфейсу",
        value = selectedLanguage,
        onClick = { showSheet = true }
    )
}

