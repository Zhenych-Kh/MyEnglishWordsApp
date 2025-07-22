package ua.ievetroy.myapplicationa.ui.screens.main.bars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ua.ievetroy.myapplicationa.ui.screens.main.buttons.ContextMenuButton
import ua.ievetroy.myapplicationa.ui.screens.main.buttons.RotateCardButtonTopBar
import ua.ievetroy.myapplicationa.ui.screens.main.buttons.SettingsButton

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onFlip: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ContextMenuButton()
        RotateCardButtonTopBar(onClick = onFlip)
        SettingsButton(onClick = onSettingsClick)
    }
}