package ua.ievetroy.myapplicationa.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.ui.theme.AppTypography

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SettingsItem(
    title: String,
    value: String,
    onClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    color = Color.Gray
                ),
                onClick = onClick
            )
            .padding(vertical = 30.dp, horizontal = 30.dp)
    ) {
        val isCompact = maxWidth < 360.dp

        if (isCompact) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = title, style = AppTypography.wordTitleSettings())
                Text(text = value, style = AppTypography.wordSheetSettings())
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, style = AppTypography.wordTitleSettings())
                Text(text = value, style = AppTypography.wordSheetSettings())
            }
        }
    }

    SettingsDivider()
}


@Composable
fun SettingsDivider() {
    Divider(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
    )
}
