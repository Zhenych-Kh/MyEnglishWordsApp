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
    selectedThemeCode: String,
    onSelect: (String) -> Unit
) {
    var show by remember { mutableStateOf(false) }
    val name = stringResource(ThemeUtils.codeToStringRes.getValue(selectedThemeCode))

    if (show) {
        ThemeBottomSheet(
            selectedThemeCode = selectedThemeCode,
            onSelect = { code -> onSelect(code); show = false },
            onDismiss = { show = false }
        )
    }

    SettingsItem(
        title = stringResource(R.string.theme),
        value = name,
        onClick = { show = true }
    )
}




