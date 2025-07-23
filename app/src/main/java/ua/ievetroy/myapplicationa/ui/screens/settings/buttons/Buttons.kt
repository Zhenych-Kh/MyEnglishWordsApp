package ua.ievetroy.myapplicationa.ui.screens.settings.buttons

import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ua.ievetroy.myapplicationa.R
import ua.ievetroy.myapplicationa.ui.buttons.AdaptiveButton
import ua.ievetroy.myapplicationa.ui.theme.AppDimens

@Composable
fun exitButton(onClick: () -> Unit) {
    AdaptiveButton(
        onClick = onClick,
        iconResId = R.drawable.close,
        buttonSize = AppDimens.ExitButton.sizeButton
    )
}

@Preview()
@Composable
fun ButtonPreview() {
    exitButton(onClick = {})
}

