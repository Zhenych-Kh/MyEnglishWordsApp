package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.resetProgress

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsItem

@Composable
fun SettingsResetProgress(onResetConfirmed: () -> Unit = {}) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(R.string.reset_progress)) },
            text = { Text(stringResource(R.string.reset_confirm_question),) },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onResetConfirmed()
                }) {
                    Text(stringResource(R.string.confirm), color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    SettingsItem(
        title = stringResource(R.string.reset_progress),
        value = "",
        onClick = { showDialog = true }
    )
}
