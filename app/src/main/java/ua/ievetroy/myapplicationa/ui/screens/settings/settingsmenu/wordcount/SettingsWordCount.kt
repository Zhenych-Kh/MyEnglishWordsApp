package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.wordcount

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsItem

@Composable
fun SettingsWordCount(
    selectedWordsPerDay: Int,
    onSelect: (Int) -> Unit
) {
    println("🌐 SettingsWordCount called with: $selectedWordsPerDay")
    var showBottomSheet by remember { mutableStateOf(false) }
    val wordOptions = listOf(5, 7, 10)

    val wordsText = stringResource(R.string.words, selectedWordsPerDay)

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
        title = stringResource(R.string.words_per_day),
        value = wordsText,
        onClick = { showBottomSheet = true }
    )
}


