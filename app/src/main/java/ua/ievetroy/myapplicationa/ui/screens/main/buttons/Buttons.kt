package ua.ievetroy.myapplicationa.ui.screens.main.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.buttons.AdaptiveButton
import ua.ievetroy.myapplicationa.ui.buttons.AdaptiveButtonVoice
import ua.ievetroy.myapplicationa.ui.buttons.AdaptiveIconButton
import ua.ievetroy.myapplicationa.ui.buttons.AdaptiveTextButton
import ua.ievetroy.myapplicationa.ui.theme.AppDimens

@Composable
fun SettingsButton(onClick: () -> Unit) {
    AdaptiveButton(
        onClick = onClick,
        iconResId = R.drawable.settings,
        buttonSize = AppDimens.ButtonSettings.sizeButton
    )
}

@Composable
fun ContextMenuButton(onClick: () -> Unit) {
    AdaptiveButton(
        onClick = onClick,
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