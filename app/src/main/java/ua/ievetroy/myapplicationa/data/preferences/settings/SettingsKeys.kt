package ua.ievetroy.myapplicationa.data.preferences.settings

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object SettingsKeys {
    val WORDS_PER_DAY = intPreferencesKey("words_per_day")
    val LANGUAGE = stringPreferencesKey("language")
    val THEME = stringPreferencesKey("theme")
    val TOGGLE_EXAMPLE = booleanPreferencesKey("toggle_example")
}