package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.language

import android.content.Context
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.MainActivity
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.LocaleHelper
import ua.ievetroy.myapplicationa.ui.screens.settings.components.SettingsOptionItem
import ua.ievetroy.myapplicationa.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageBottomSheet(
    selectedLanguage: String,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scrollState = rememberScrollState()

    val context = LocalContext.current
    val activity = LocalActivity.current

    val languageDisplayNames = mapOf(
        "uk" to stringResource(R.string.lang_ukrainian),
        "ru" to stringResource(R.string.lang_russian),
        "pl" to stringResource(R.string.lang_polish),
        "es" to stringResource(R.string.lang_spanish),
        "pt" to stringResource(R.string.lang_portuguese),
        "zh" to stringResource(R.string.lang_chinese),
        "ja" to stringResource(R.string.lang_japanese),
        "ko" to stringResource(R.string.lang_korean),
        "tr" to stringResource(R.string.lang_turkish),
        "fr" to stringResource(R.string.lang_french),
        "de" to stringResource(R.string.lang_german)
    )

    var pendingLanguageCode by remember { mutableStateOf<String?>(null) }

    // ⏺ Запускаємо перезапуск, коли обрано мову
    LaunchedEffect(pendingLanguageCode) {
        pendingLanguageCode?.let { langCode ->
            // Зберігаємо в SharedPreferences
            context.getSharedPreferences("settings", Context.MODE_PRIVATE)
                .edit()
                .putString("language_code", langCode)
                .apply()

            // Оновлюємо локаль
            val newContext = LocaleHelper.updateLocale(context, langCode)

            // Створюємо новий intent
            val intent = Intent(newContext, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }

            newContext.startActivity(intent)
            activity?.finish() // Завершуємо поточну активність

            pendingLanguageCode = null
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = AppColors.AppBackgraundsTheme.BackgraundsLightsettings
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(vertical = 8.dp)
        ) {
            languageDisplayNames.entries.forEachIndexed { index, entry ->
                val code = entry.key
                val displayName = entry.value

                SettingsOptionItem(
                    title = displayName,
                    isSelected = (code == selectedLanguage),
                    onClick = {
                        onSelect(code)
                        onDismiss()

                        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
                            .edit()
                            .putString("language_code", code)
                            .apply()

                        LocaleHelper.updateLocale(context, code)

                        // Плавно перезапускаємо активність
                        activity?.recreate()
                    }
                )


                if (index != languageDisplayNames.size - 1) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        thickness = 1.dp,
                        color = Color(0xFFE0E0E0)
                    )
                }
            }
        }
    }
}



