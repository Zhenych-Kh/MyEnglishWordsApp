package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.screens.settings.components.SettingsOptionItem
import ua.ievetroy.myapplicationa.ui.theme.AppColors
import ua.ievetroy.myapplicationa.ui.theme.AppDimens.WordCard.cornerRadius

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeBottomSheet(
    selectedThemeCode: String,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val themeDisplayNames = ThemeUtils.codeToStringRes.mapValues {
        stringResource(it.value)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(cornerRadius)
    ) {
        LazyColumn {
            itemsIndexed(ThemeUtils.codeToStringRes.keys.toList()) { index, code ->
                SettingsOptionItem(
                    title = themeDisplayNames[code] ?: code,
                    isSelected = (code == selectedThemeCode),
                    onClick = { onSelect(code) }
                )

                if (index != ThemeUtils.codeToStringRes.size - 1) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                }
            }
        }
    }
}


