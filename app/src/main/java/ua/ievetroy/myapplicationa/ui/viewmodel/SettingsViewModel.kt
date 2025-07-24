package ua.ievetroy.myapplicationa.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ua.ievetroy.myapplicationa.data.preferences.settings.SettingsRepository

class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    val wordsPerDay = repository.wordsPerDay.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 5
    )

    val selectedLanguage = repository.language.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "Українська"
    )

    val selectedTheme = repository.theme.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "Світла"
    )

    val toggleExample = repository.toggleExample.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun setToggleExample(enabled: Boolean) {
        viewModelScope.launch {
            repository.setToggleExample(enabled)
        }
    }


    fun setWordsPerDay(count: Int) {
        viewModelScope.launch {
            repository.setWordsPerDay(count)
        }
    }

    fun setLanguage(language: String) {
        viewModelScope.launch {
            repository.setLanguage(language)
        }
    }

    fun setTheme(theme: String) {
        viewModelScope.launch {
            repository.setTheme(theme)
        }
    }

    fun resetProgress() {
        viewModelScope.launch {
            // Тут буде логіка очищення прогресу
        }
    }
}