package ua.ievetroy.myapplicationa.ui.screens.main.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppDivider(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFBDBDBD),
    thickness: Dp = 1.dp,
    paddingHorizontal: Dp = 0.dp,
    paddingVertical: Dp = 5.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
            .height(thickness)
            .background(color = color)
    )
}
