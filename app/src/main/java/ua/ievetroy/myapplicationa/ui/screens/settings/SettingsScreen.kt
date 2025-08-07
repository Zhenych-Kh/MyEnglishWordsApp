package ua.ievetroy.myapplicationa.ui.screens.settings

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.ievetroy.myapplicationa.data.preferences.settings.SettingsRepository
import ua.ievetroy.myapplicationa.ui.screens.settings.buttons.exitButton
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.language.SettingsLanguage
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.resetProgress.SettingsResetProgress
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.theme.SettingsTheme
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.toggle.SettingsToggle
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.wordcount.SettingsWordCount
import ua.ievetroy.myapplicationa.ui.theme.AppModifiers
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModel
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModelFactory

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    repository: SettingsRepository // ← новий параметр
) {
    var isClosing by remember { mutableStateOf(false) }

    val viewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(repository)
    )

    // Зчитування стану з ViewModel
    val selectedLanguage by viewModel.selectedLanguage.collectAsState()
    val wordsPerDay by viewModel.wordsPerDay.collectAsState()
    val selectedTheme by viewModel.selectedTheme.collectAsState()

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
                exitButton(onClick = {
                    if (!isClosing) {
                        isClosing = true
                        onBack()
                    }
                })
            }
        }

        item {
            val activity = LocalActivity.current

            SettingsLanguage(
                selectedLanguage = selectedLanguage,
                onSelect = { langCode ->
                    viewModel.setLanguage(langCode)
                    activity?.recreate() // перезапуск з новою локаллю
                }
            )
        }

        item {
            SettingsWordCount(
                selectedWordsPerDay = wordsPerDay,
                onSelect = { viewModel.setWordsPerDay(it) }
            )
        }

        item {
            SettingsToggle(
                isEnabled = viewModel.toggleExample.collectAsState().value,
                onToggle = { viewModel.setToggleExample(it) }
            )
        }

        item {
            SettingsTheme(
                selectedTheme = selectedTheme,
                onSelect = { viewModel.setTheme(it) }
            )
        }

        item {
            SettingsResetProgress(
                onResetConfirmed = {
                    viewModel.resetProgress()
                }
            )
        }
    }
}