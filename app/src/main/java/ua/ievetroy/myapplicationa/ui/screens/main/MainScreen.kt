package ua.ievetroy.myapplicationa.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import ua.ievetroy.myapplicationa.ui.SwipeWordCardPager
import ua.ievetroy.myapplicationa.ui.screens.main.bars.BottomBar
import ua.ievetroy.myapplicationa.ui.screens.main.bars.TopBar
import ua.ievetroy.myapplicationa.ui.screens.main.components.ContextMenuSheet
import ua.ievetroy.myapplicationa.ui.theme.AppModifiers
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModel
import ua.ievetroy.myapplicationa.ui.viewmodel.wordViewModel.WordViewModel
import ua.ievetroy.myapplicationa.ui.viewmodel.wordViewModel.WordViewModelFactory
import ua.ievetroy.myapplicationa.ui.screens.main.util.SetStatusBarColors
import ua.ievetroy.myapplicationa.ui.screens.main.util.provideWordOrderRepository
import ua.ievetroy.myapplicationa.ui.screens.main.util.provideWordRepository
import ua.ievetroy.myapplicationa.ui.screens.settings.settingsmenu.wordcount.SettingsWordCount

@Composable
fun MainScreen(
    onSettingsClick: () -> Unit,
    settingsViewModel: SettingsViewModel
) {
    SetStatusBarColors(isDarkTheme = false)

    val context = LocalContext.current
    val wordRepository = remember { provideWordRepository(context) }
    val wordOrderRepository = remember { provideWordOrderRepository(context) }
    val viewModel: WordViewModel = viewModel(
        factory = WordViewModelFactory(wordRepository, wordOrderRepository)
    )

    // --- State ---
    val wordsPerDay by settingsViewModel.wordsPerDay.collectAsState()
    val words by viewModel.words.collectAsState()
    val firstVisibleIndex by viewModel.firstVisibleIndex.collectAsState()
    var isFlipped by remember { mutableStateOf(false) }
    var showContextMenu by remember { mutableStateOf(false) }

    // --- Видимі слова ---
    val startIndex = firstVisibleIndex
    val endIndex = (startIndex + wordsPerDay).coerceAtMost(words.size)
    val visibleWords = if (startIndex < words.size) words.subList(startIndex, endIndex) else emptyList()

    // Додаємо стан swipe
    var swipeTrigger by remember { mutableStateOf(false) }


    // --- INIT ---
    LaunchedEffect(Unit) {
        viewModel.init()
    }

    // --- Логіка НЕ скидати картку при зміні wordsPerDay ---
    var prevWordsPerDay by rememberSaveable { mutableStateOf(wordsPerDay) }
    LaunchedEffect(wordsPerDay) {
        if (prevWordsPerDay != wordsPerDay) {
            // нічого не змінюємо — просто вікно стає ширшим або вужчим!
            prevWordsPerDay = wordsPerDay
        }
    }

    // --- UI ---
    if (showContextMenu) {
        ContextMenuSheet(
            onNext = {
                swipeTrigger = true
                showContextMenu = false
            },
            onDismiss = { showContextMenu = false }
        )
    }

    Column(
        modifier = AppModifiers.rootColumnModifier
    ) {
        TopBar(
            modifier = AppModifiers.topBarModifier,
            onFlip = { isFlipped = !isFlipped },
            onSettingsClick = onSettingsClick,
            onContextClick = { showContextMenu = true }
        )
        Box(modifier = Modifier
            .weight(1f)) {
            SwipeWordCardPager(
                key = firstVisibleIndex,
                words = visibleWords,
                isFlipped = isFlipped,
                onFlip = { isFlipped = !isFlipped },
                onNext = { viewModel.nextCard(wordsPerDay) },
                swipeTrigger = swipeTrigger,
                onSwipeConsumed = { swipeTrigger = false }, // скинемо тригер
                modifier = Modifier
                    .fillMaxSize()
                    .then(AppModifiers.wordCardModifier)
            )



        }
        BottomBar(
            AppModifiers.bottomBarModifier
        )
    }
}






