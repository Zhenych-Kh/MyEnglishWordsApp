package ua.ievetroy.myapplicationa.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.ui.screens.settings.buttons.exitButton
import ua.ievetroy.myapplicationa.ui.theme.AppModifiers

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Column(
        modifier = AppModifiers.rootColumnModifier
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            exitButton(onClick = onBack)
        }

        SettingsWordCount()
    }
}


@Preview
@Composable
fun PrewiewSettingsScreen() {
    SettingsScreen(onBack = {})
}