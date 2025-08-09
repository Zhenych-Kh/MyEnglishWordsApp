package ua.ievetroy.myapplicationa.ui.screens.main

import androidx.compose.animation.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.ui.screens.main.divider.AppDivider
import ua.ievetroy.myapplicationa.ui.theme.AppDimens
import ua.ievetroy.myapplicationa.ui.theme.AppTypography
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.ui.input.pointer.pointerInput
import ua.ievetroy.myapplicationa.ui.screens.main.buttons.VoiceActingButton
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationResult
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch

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


