package ua.ievetroy.myapplicationa.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.ui.screens.main.buttons.VoiceActingButton
import ua.ievetroy.myapplicationa.ui.screens.main.divider.AppDivider
import ua.ievetroy.myapplicationa.ui.theme.AppDimens
import ua.ievetroy.myapplicationa.ui.theme.AppTypography

@Composable
fun WordItem(
    word: Word,
    onSwipeLeft: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope() // ← Додаємо Scope

    val swipeThreshold = 530f

    Box(
        modifier = modifier
            .offset { IntOffset(offsetX.value.toInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (offsetX.value <= -swipeThreshold) {
                            onSwipeLeft()
                        }
                        // Плавно повертаємо назад
                        scope.launch {
                            offsetX.animateTo(0f)
                        }
                    },
                    onHorizontalDrag = { _, dragAmount ->
                        scope.launch {
                            val newOffset = (offsetX.value + dragAmount).coerceAtMost(0f)
                            offsetX.snapTo(newOffset)
                        }
                    }
                )
            }
            .clip(RoundedCornerShape(4.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true, color = Color.Gray)
            ) {
                isExpanded = !isExpanded
            }
    ) {
        // Увесь інший контент залишається як є
        Column {
            Row {
                Text(
                    text = word.word,
                    style = AppTypography.wordTitle()
                )
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Row(
                        modifier = Modifier.padding(
                            start = AppDimens.WordItem.paddingSidesListenButton,
                            top = AppDimens.WordItem.paddingTopListenButton
                        )
                    ) {
                        VoiceActingButton()
                    }
                }
            }
            Text(
                text = word.translation,
                style = AppTypography.wordTranslation()
            )

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column {
                    Spacer(modifier = Modifier.height(AppDimens.WordItem.paddingTopExample))
                    Text(
                        text = word.example,
                        style = AppTypography.exampleSentence()
                    )
                    AppDivider(
                        thickness = 1.dp,
                        color = Color(0xFFBDBDBD),
                    )
                }
            }
        }
    }
}


