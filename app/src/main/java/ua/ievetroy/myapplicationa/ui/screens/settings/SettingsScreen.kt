package ua.ievetroy.myapplicationa.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.ui.screens.settings.buttons.exitButton
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.language.SettingsLanguage
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.resetProgress.SettingsResetProgress
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.theme.SettingsTheme
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.toggle.SettingsToggle
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.wordcount.SettingsWordCount
import ua.ievetroy.myapplicationa.ui.theme.AppModifiers

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    LazyColumn(
        modifier = AppModifiers.rootColumnModifier
            .padding(top = 16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                exitButton(onClick = onBack)
            }
        }

        item { SettingsLanguage() }
        item { SettingsWordCount() }
        item { SettingsToggle() }
        item { SettingsTheme() }
        item {
            SettingsResetProgress(
                onResetConfirmed = {
                    // TODO: Очистити дані прогресу (наприклад, через ViewModel або DataStore)
                }
            )
        }
    }
}



@Preview
@Composable
fun PrewiewSettingsScreen() {
    SettingsScreen(onBack = {})
}