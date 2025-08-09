package ua.ievetroy.myapplicationa.data.preferences.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(private val context: Context) {


    val wordsPerDay: Flow<Int> = context.settingsDataStore.data
        .map { it[SettingsKeys.WORDS_PER_DAY] ?: 5 }

    val language: Flow<String> = context.settingsDataStore.data
        .map { it[SettingsKeys.LANGUAGE] ?: "uk" } // ← "uk" — мова за замовчуванням

    val theme: Flow<String> = context.settingsDataStore.data
        .map { it[SettingsKeys.THEME] ?: "system" }

    val toggleExample: Flow<Boolean> = context.settingsDataStore.data
        .map { it[SettingsKeys.TOGGLE_EXAMPLE] ?: false }


    suspend fun setToggleExample(enabled: Boolean) {
        context.settingsDataStore.edit {
            it[SettingsKeys.TOGGLE_EXAMPLE] = enabled
        }
    }

    suspend fun setWordsPerDay(count: Int) {
        context.settingsDataStore.edit {
            it[SettingsKeys.WORDS_PER_DAY] = count
        }
    }

    suspend fun setLanguage(langCode: String) {
        context.settingsDataStore.edit {
            it[SettingsKeys.LANGUAGE] = langCode
        }

        // Зберігаємо також у SharedPreferences — для attachBaseContext
        context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
            .putString("language_code", langCode)
            .apply()
    }

    suspend fun setTheme(code: String) {
        context.settingsDataStore.edit { it[SettingsKeys.THEME] = code }
    }
}