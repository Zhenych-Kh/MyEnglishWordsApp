package ua.ievetroy.myapplicationa.ui.components.bars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.ievetroy.myapplicationa.ui.components.buttons.HomeButton
import ua.ievetroy.myapplicationa.ui.components.buttons.LibraryButton

@Preview
@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        HomeButton()
        LibraryButton()
    }
}