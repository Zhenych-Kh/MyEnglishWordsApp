package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.screens.settings.LanguageUtils
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsItem

@Composable
fun SettingsLanguage(
    selectedLanguage: String,
    onSelect: (String) -> Unit
) {
    var showSheet by remember { mutableStateOf(false) }

    // Отримуємо назви мов із ресурсів
    val languageDisplayNames = LanguageUtils.languageCodeToStringRes.mapValues {
        stringResource(it.value)
    }

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

    // Покажемо локалізовану назву мови замість коду (наприклад "Українська" замість "uk")
    SettingsItem(
        title = stringResource(id = R.string.interface_language),
        value = languageDisplayNames[selectedLanguage] ?: selectedLanguage,
        onClick = { showSheet = true }
    )
}


