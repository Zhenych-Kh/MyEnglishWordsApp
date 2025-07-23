package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.language

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
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
    val languages = listOf(
        "Українська", "Польська", "Іспанська", "Португальська",
        "Китайська", "Японська", "Корейська", "Турецька",
        "Французька", "Німецька"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = AppColors.AppBackgraundsTheme.BackgraundsLight
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            itemsIndexed(languages) { index, language ->
                SettingsOptionItem(
                    title = language,
                    isSelected = (language == selectedLanguage),
                    onClick = { onSelect(language) }
                )

                if (index != languages.lastIndex) {
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
