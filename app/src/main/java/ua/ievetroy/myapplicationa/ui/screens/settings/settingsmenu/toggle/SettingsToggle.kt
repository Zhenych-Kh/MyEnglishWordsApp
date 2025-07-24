package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.toggle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsDivider
import ua.ievetroy.myapplicationa.ui.theme.AppTypography

@Composable
fun SettingsToggle(
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp, horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Автооновлення слів", style = AppTypography.wordTitleSettings())
        Switch(
            checked = isEnabled,
            onCheckedChange = { onToggle(it) }  // ← ViewModel update
        )
    }

    SettingsDivider()
}
