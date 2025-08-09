package ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.wordcount

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.screens.settings.components.SettingsOptionItem
import ua.ievetroy.myapplicationa.ui.theme.AppColors
import ua.ievetroy.myapplicationa.ui.theme.AppDimens.WordCard.cornerRadius


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordCountBottomSheet(
    options: List<Int>,
    selected: Int,
    onSelect: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(cornerRadius)
    ) {
        LazyColumn {
            itemsIndexed(options) { index, option ->
                SettingsOptionItem(
                    title = stringResource(R.string.words, option),
                    isSelected = (option == selected),
                    onClick = { onSelect(option) }
                )

                if (index != options.lastIndex) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 20.dp),
                    )
                }
            }
        }
    }
}
