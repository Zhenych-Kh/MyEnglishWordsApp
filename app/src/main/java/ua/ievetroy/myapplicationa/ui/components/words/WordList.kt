package ua.ievetroy.myapplicationa.ui.components.words

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.ui.theme.AppDimens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun WordList(words: List<Word>) {
    val minSpacing = 5.dp
    val count = words.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = AppDimens.WordCard.paddingBottom)
            .padding(
                horizontal = AppDimens.WordItem.paddingSides,
                vertical = AppDimens.WordItem.paddingTop
            )
    ) {
        words.forEachIndexed { index, word ->
            var isExpanded by remember { mutableStateOf(false) }

            WordItem(
                word = word,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded }
            )

            if (index != count - 1) {
                // Фіксована частина
                Spacer(modifier = Modifier.height(minSpacing))

                // Анімована частина — змінюється в залежності від того, чи розкрито слово
                val animatedSpacer by animateDpAsState(
                    targetValue = if (isExpanded) 0.dp else 1.dp,
                    label = "animatedSpacing"
                )

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(animatedSpacer)
                    .weight(1f, fill = true)
                )
            }
        }
    }
}

