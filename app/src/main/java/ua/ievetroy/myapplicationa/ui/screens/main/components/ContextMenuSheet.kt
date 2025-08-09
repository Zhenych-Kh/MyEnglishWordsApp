package ua.ievetroy.myapplicationa.ui.screens.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.Multimaps.index
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.RequestBuilder.options
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.screens.settings.SettingsDivider
import ua.ievetroy.myapplicationa.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContextMenuSheet(
    isFlipped: Boolean,
    onNext: () -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(vertical = 8.dp)
        ) {
            val textColor = if (isFlipped) Color.Gray else Color.Black
            val alpha = if (isFlipped) 0.5f else 1f

            Text(
                stringResource(id = R.string.next_card),
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(alpha)
                    .clickable(
                        enabled = !isFlipped,  // ← блокування свайпу при isFlipped
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = true, color = Color.Gray),
                        onClick = {
                            onNext()
                        }
                    )
                    .padding(vertical = 24.dp, horizontal = 20.dp),
                style = AppTypography.wordSheetSettings(),
                color = MaterialTheme.colorScheme.onSurface
            )
            Divider(
                modifier = Modifier.padding(horizontal = 20.dp),
            )
        }
    }
}


