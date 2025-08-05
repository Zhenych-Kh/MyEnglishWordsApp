package ua.ievetroy.myapplicationa.ui.screens.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.ui.theme.AppDimens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clipToBounds
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.ievetroy.myapplicationa.ui.viewmodel.wordViewModel.WordViewModel

@Composable
fun WordList(
    words: List<Word>,
    onSwipeLeft: (index: Int) -> Unit
) {
    val minSpacing = 5.dp
    val count = words.size

    // Найнадійніше збереження стану розгортання
    val expandedMap = remember { mutableStateMapOf<Int, Boolean>() }
    val coroutineScope = rememberCoroutineScope()
    var isSwipeLocked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clipToBounds()
            .verticalScroll(rememberScrollState())
            .padding(bottom = AppDimens.WordCard.paddingBottom)
            .padding(
                horizontal = AppDimens.WordItem.paddingSides,
                vertical = AppDimens.WordItem.paddingTop
            )
    ) {
        words.forEachIndexed { index, word ->
            val isExpanded = expandedMap[index] ?: false
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                     // <-- Обрізає вміст по межах
            ) {
                WordItem(
                    word = word,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expandedMap[index] = !isExpanded
                        },
                    onSwipeLeft = {
                        if (!isSwipeLocked) {
                            isSwipeLocked = true
                            onSwipeLeft(index)
                            coroutineScope.launch {
                                delay(100)
                                isSwipeLocked = false
                            }
                        }
                    }
                )
            }

            if (index != count - 1) {
                Spacer(modifier = Modifier.height(minSpacing))

                val animatedSpacer by animateDpAsState(
                    targetValue = if (isExpanded) 0.dp else 1.dp,
                    label = "animatedSpacing"
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(animatedSpacer)
                        .weight(1f, fill = true)
                )
            }
        }
    }
}


