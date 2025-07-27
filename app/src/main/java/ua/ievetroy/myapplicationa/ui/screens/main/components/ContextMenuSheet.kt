package ua.ievetroy.myapplicationa.ui.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContextMenuSheet(
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Наступне слово",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // TODO: Реалізуй дію тут
                        onDismiss()
                    }
                    .padding(vertical = 12.dp, horizontal = 20.dp),
                style = AppTypography.wordTitleSettings()
            )
        }
    }
}
