package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsItem

@Composable
fun SettingsTheme(
    selectedTheme: String,
    onSelect: (String) -> Unit
) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ThemeBottomSheet(
            selectedTheme = selectedTheme,
            onSelect = {
                onSelect(it)            // ← ViewModel update
                showSheet = false
            },
            onDismiss = { showSheet = false }
        )
    }

    SettingsItem(
        title = stringResource(R.string.theme),
        value = selectedTheme,
        onClick = { showSheet = true }
    )
}


