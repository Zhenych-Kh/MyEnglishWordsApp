package ua.ievetroy.myapplicationa.ui.components.words

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.data.dummy.sampleWords
import ua.ievetroy.myapplicationa.ui.components.buttons.RotateCardButtonWordCard
import ua.ievetroy.myapplicationa.ui.theme.AppDimens
import ua.ievetroy.myapplicationa.ui.theme.AppModifiers
import ua.ievetroy.myapplicationa.ui.components.shadows.uniformShadow

@Composable
fun WordCard(modifier: Modifier = Modifier) {
    val cornerRadius = AppDimens.WordCard.cornerRadius

    Box(
        modifier = modifier
            .uniformShadow(
                alpha = 0.3f,
                cornerRadius = cornerRadius,
                blurRadius = 20.dp,
                yOffset = 9f,
                xOffset = -10f
            )
            .background(Color.White, shape = RoundedCornerShape(cornerRadius))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = AppDimens.FlipButton.paddingTop,
                        end = AppDimens.FlipButton.paddingEnd
                    ),
                horizontalArrangement = Arrangement.End
            ) {
                RotateCardButtonWordCard()
            }

            WordList(words = sampleWords)
        }
    }
}
