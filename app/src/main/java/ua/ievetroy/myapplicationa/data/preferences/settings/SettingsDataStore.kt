package ua.ievetroy.myapplicationa.data.preferences.settings

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.settingsDataStore by preferencesDataStore(name = "settings")
