package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.wordcount

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsItem

@Composable
fun SettingsWordCount(
    selectedWordsPerDay: Int,
    onSelect: (Int) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val wordOptions = listOf(5, 7, 10)

    if (showBottomSheet) {
        WordCountBottomSheet(
            options = wordOptions,
            selected = selectedWordsPerDay,
            onSelect = {
                onSelect(it)
                showBottomSheet = false
            },
            onDismiss = { showBottomSheet = false }
        )
    }

    SettingsItem(
        title = "Кількість слів/день",
        value = "$selectedWordsPerDay слів",
        onClick = { showBottomSheet = true }
    )
}

