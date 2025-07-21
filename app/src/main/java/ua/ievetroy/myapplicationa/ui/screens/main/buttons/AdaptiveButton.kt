package ua.ievetroy.myapplicationa.ui.screens.main.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun AdaptiveButton(
    onClick: () -> Unit,
    iconResId: Int,
    buttonSize: Dp,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    IconButton(onClick = onClick,
        modifier = Modifier
            .size(buttonSize)
    ) {
        Image(
            painter = painterResource(iconResId),
            contentDescription = contentDescription,
        )
    }
}

@Composable
fun AdaptiveIconButton(
    onClick: () -> Unit,
    iconResId: Int,
    contentDescription: String? = null,
    buttonSize: Dp,
    showButton: Boolean,
    modifier: Modifier = Modifier
) {
    if (showButton) {
        IconButton(onClick = onClick,
            modifier = Modifier
                .size(buttonSize)
        ) {
            Image(
                painter = painterResource(iconResId),
                contentDescription = contentDescription,
            )
        }
    }
}

@Composable
fun AdaptiveButtonVoice(
    onClick: () -> Unit,
    iconResId: Int,
    buttonSize: Dp,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    IconButton(onClick = onClick,
        modifier = Modifier
            .size(buttonSize)
    ) {
        Image(
            painter = painterResource(iconResId),
            contentDescription = contentDescription,
        )
    }
}
