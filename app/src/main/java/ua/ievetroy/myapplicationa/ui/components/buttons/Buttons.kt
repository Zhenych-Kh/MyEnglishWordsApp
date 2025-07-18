package ua.ievetroy.myapplicationa.ui.components.buttons

import android.R.attr.onClick
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.theme.AppDimens

@Preview
@Composable
fun SettingsButton() {
    AdaptiveButton(
        onClick = {},
        iconResId = R.drawable.settings,
        buttonSize = AppDimens.ButtonSettings.sizeButton
    )
}

@Preview
@Composable
fun ContextMenuButton() {
    AdaptiveButton(
        onClick = {},
        iconResId = R.drawable._contextmenu,
        buttonSize = AppDimens.ContextMenuButton.sizeButton
    )
}

@Composable
fun RotateCardButtonWordCard(onClick: () -> Unit) {
    AdaptiveIconButton(
        onClick = onClick,
        iconResId = R.drawable._flip,
        buttonSize = AppDimens.FlipButton.sizeButton,
        showButton = AppDimens.screenHeightDp >= 450,
    )
}

@Composable
fun RotateCardButtonTopBar(onClick: () -> Unit) {
    AdaptiveIconButton(
        onClick = onClick,
        iconResId = R.drawable._flip,
        buttonSize = AppDimens.FlipButton.sizeButton,
        showButton = AppDimens.screenHeightDp <= 450,
    )
}

@Preview
@Composable
fun HomeButton() {
    AdaptiveTextButton(
        onClick = {},
        iconResId = R.drawable._homelearnpage,
        text = "Вчити",
        iconSize = AppDimens.HomeButton.sizeIcon,
        showButton = AppDimens.screenHeightDp >= 450,
    )
}


@Preview
@Composable
fun LibraryButton() {
    AdaptiveTextButton(
        onClick = {},
        iconResId = R.drawable._librarypage,
        text = "Словник",
        iconSize = AppDimens.LibraryButton.sizeIcon,
        showButton = AppDimens.screenHeightDp >= 450,
    )
}

@Preview
@Composable
fun VoiceActingButton() {
    AdaptiveButtonVoice(
        onClick = {},
        iconResId = R.drawable._listenword,
        buttonSize = AppDimens.ListenWordButton.sizeIcon,
    )
}