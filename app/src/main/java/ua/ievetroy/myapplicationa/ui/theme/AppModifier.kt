package ua.ievetroy.myapplicationa.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
                top = AppDimens.BottomBar.paddingTop,
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
            .background(AppColors.AppBackgraundsTheme.BackgraundsLight)
            .statusBarsPadding()
            .navigationBarsPadding()
}
