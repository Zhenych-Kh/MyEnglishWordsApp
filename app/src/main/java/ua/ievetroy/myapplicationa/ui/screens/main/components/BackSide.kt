package ua.ievetroy.myapplicationa.ui.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.ui.screens.main.buttons.RotateCardButtonWordCard
import ua.ievetroy.myapplicationa.ui.theme.AppDimens
import ua.ievetroy.myapplicationa.ui.theme.AppTypography

@Composable
fun BackSide(onFlip: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = AppDimens.FlipButton.paddingTop,
                    end = AppDimens.FlipButton.paddingEnd
                ),
            horizontalArrangement = Arrangement.End
        ) {
            RotateCardButtonWordCard(onClick = onFlip)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Це зворотна сторона картки",
            style = AppTypography.wordTitle()
        )
        // Додай ще що хочеш
    }
}
