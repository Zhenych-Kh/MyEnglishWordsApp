package ua.ievetroy.myapplicationa.ui.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import ua.ievetroy.myapplicationa.ui.theme.AppColors
import ua.ievetroy.myapplicationa.ui.theme.AppTypography

@Composable
fun AdaptiveTextButton(
    onClick: () -> Unit,
    iconResId: Int,
    text: String,
    iconSize: Dp,
    showButton: Boolean,
    modifier: Modifier = Modifier

) {
    if (showButton) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(Color.Transparent),
        ) {
            Column(
                modifier = modifier.wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showButton) {
                    Image(
                        modifier = Modifier.size(iconSize),
                        painter = painterResource(id = iconResId),
                        contentDescription = text
                    )
                    Text(
                        text = text,
                        fontSize = AppTypography.labelSmallFontSize,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = AppTypography.labelSmall().fontWeight
                    )
                }
            }
        }
    }
}


