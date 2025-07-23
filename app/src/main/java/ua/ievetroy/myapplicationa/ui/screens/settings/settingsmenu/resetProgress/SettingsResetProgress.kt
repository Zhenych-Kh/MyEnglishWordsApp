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
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsItem

@Composable
fun SettingsResetProgress(onResetConfirmed: () -> Unit = {}) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Очистити прогрес") },
            text = { Text("Ви впевнені, що хочете очистити весь прогрес? Цю дію неможливо скасувати.") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onResetConfirmed()
                }) {
                    Text("Так", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Скасувати")
                }
            }
        )
    }

    SettingsItem(
        title = "Очистити прогрес",
        value = "",
        onClick = { showDialog = true }
    )
}
