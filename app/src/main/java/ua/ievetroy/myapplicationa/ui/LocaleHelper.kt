package ua.ievetroy.myapplicationa.ui

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocaleHelper {

    fun updateLocale(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)

        return context.createConfigurationContext(configuration)
    }
}
