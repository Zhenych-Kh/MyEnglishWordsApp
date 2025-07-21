package ua.ievetroy.myapplicationa.ui.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.ievetroy.myapplicationa.data.dummy.sampleWords
import ua.ievetroy.myapplicationa.ui.screens.main.WordList
import ua.ievetroy.myapplicationa.ui.screens.main.buttons.RotateCardButtonWordCard
import ua.ievetroy.myapplicationa.ui.theme.AppDimens

@Composable
fun FrontSide(onFlip: () -> Unit) {
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
            RotateCardButtonWordCard(onClick = onFlip)
        }

        WordList(words = sampleWords)
    }
}
