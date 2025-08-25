package ua.ievetroy.myapplicationa.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.ievetroy.myapplicationa.ui.theme.AppDimens.WordCard.cornerRadius

object AppModifiers {

    val topBarModifier: Modifier
        @Composable get() = Modifier
            .fillMaxWidth()
            .padding(
                start = AppDimens.TopBar.paddingSides,
                top = AppDimens.TopBar.paddingTop,
                end = AppDimens.TopBar.paddingSides
            )

    val bottomBarModifier: Modifier
        @Composable get() = Modifier
            .fillMaxWidth()
            .padding(
                //top = AppDimens.BottomBar.paddingTop,
                start = AppDimens.BottomBar.paddingSides,
                end = AppDimens.BottomBar.paddingSides
            )

    val wordCardModifier: Modifier
        @Composable get() = Modifier
            .padding(
                start = AppDimens.WordCard.paddingSides,
                top = AppDimens.WordCard.paddingTop,
                end = AppDimens.WordCard.paddingSides,
            )

    val rootColumnModifier: Modifier
        @Composable get() = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
}
