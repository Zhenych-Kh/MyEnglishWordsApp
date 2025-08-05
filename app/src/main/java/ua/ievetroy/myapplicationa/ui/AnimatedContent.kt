package ua.ievetroy.myapplicationa.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.ui.screens.main.WordCard

@Composable
fun SwipeWordCardPager(
    key: Int,
    words: List<Word>,
    isFlipped: Boolean,
    onFlip: () -> Unit,
    onNext: () -> Unit,
    swipeTrigger: Boolean,                 // <-- новий параметр
    onSwipeConsumed: () -> Unit,
    onSwipeLeft: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (words.isEmpty()) return

    val screenWidthPx = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }

    var isAnimating by remember { mutableStateOf(false) }
    var oldWords by remember { mutableStateOf<List<Word>?>(null) }
    var oldIsFlipped by remember { mutableStateOf(false) }
    var animateOffset by remember { mutableStateOf(0f) }

    val animOffset by animateFloatAsState(
        targetValue = animateOffset,
        animationSpec = tween(300),
        finishedListener = {
            if (isAnimating) {
                isAnimating = false
                oldWords = null
                animateOffset = 0f
            }
        },
        label = "swipe"
    )

    fun startSwipe() {
        if (!isAnimating) {
            oldWords = words.toList()
            oldIsFlipped = isFlipped
            isAnimating = true
            animateOffset = screenWidthPx
            onNext()
        }
    }

    // Якщо тригер ззовні — запускаємо swipe і одразу скидаємо тригер
    LaunchedEffect(swipeTrigger) {
        if (swipeTrigger) {
            startSwipe()
            onSwipeConsumed()
        }
    }

    Box(modifier = modifier) {
        if (isAnimating) {
            WordCard(
                words = words,
                isFlipped = false,
                onFlip = {},
                onSwipeLeft = onSwipeLeft,
                modifier = Modifier.fillMaxSize()
            )
        }

        if (oldWords != null) {
            WordCard(
                words = oldWords!!,
                isFlipped = oldIsFlipped,
                onFlip = onFlip,
                onSwipeLeft = onSwipeLeft,
                modifier = Modifier
                    .fillMaxSize()
                    .offset { IntOffset(animOffset.toInt(), 0) }
            )
        } else {
            WordCard(
                words = words,
                isFlipped = isFlipped,
                onFlip = onFlip,
                onSwipeLeft = onSwipeLeft,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}






