package ua.ievetroy.myapplicationa.ui.screens.main.bars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.ievetroy.myapplicationa.ui.screens.main.buttons.HomeButton
import ua.ievetroy.myapplicationa.ui.screens.main.buttons.LibraryButton

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