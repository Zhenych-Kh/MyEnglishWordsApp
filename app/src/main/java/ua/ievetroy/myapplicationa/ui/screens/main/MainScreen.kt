package ua.ievetroy.myapplicationa.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.ievetroy.myapplicationa.data.preferences.words.WordOrderRepository
import ua.ievetroy.myapplicationa.data.repository.JsonWordRepository
import ua.ievetroy.myapplicationa.data.repository.WordRepository
import ua.ievetroy.myapplicationa.ui.screens.main.bars.BottomBar
import ua.ievetroy.myapplicationa.ui.screens.main.bars.TopBar
import ua.ievetroy.myapplicationa.ui.screens.main.components.ContextMenuSheet
import ua.ievetroy.myapplicationa.ui.theme.AppColors
import ua.ievetroy.myapplicationa.ui.theme.AppModifiers
import ua.ievetroy.myapplicationa.ui.viewmodel.settings.SettingsViewModel
import ua.ievetroy.myapplicationa.ui.viewmodel.wordViewModel.WordViewModel
import ua.ievetroy.myapplicationa.ui.viewmodel.wordViewModel.WordViewModelFactory

@Composable
fun SetStatusBarColors(isDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = if (isDarkTheme) Color.Black else AppColors.AppBackgraundsTheme.BackgraundsLight,
            darkIcons = !isDarkTheme
        )
    }
}

@Composable
fun MainScreen(
    onSettingsClick: () -> Unit,
    settingsViewModel: SettingsViewModel
) {
    val context = LocalContext.current
    val wordRepository: WordRepository = JsonWordRepository(context)
    val wordOrderRepository = remember { WordOrderRepository(context) }
    val viewModel: WordViewModel = viewModel(
        factory = WordViewModelFactory(wordRepository, wordOrderRepository)
    )

    // ----- Нове -----
    val wordsPerDay by settingsViewModel.wordsPerDay.collectAsState()
    val words by viewModel.words.collectAsState()
    val currentPage by viewModel.currentPage.collectAsState()
    // ----------------

    var isFlipped by remember { mutableStateOf(false) }
    var showContextMenu by remember { mutableStateOf(false) }

    val startIndex = currentPage * wordsPerDay
    val endIndex = (startIndex + wordsPerDay).coerceAtMost(words.size)
    val visibleWords = if (startIndex < words.size) words.subList(startIndex, endIndex) else emptyList()


    // --- Слідкуємо за wordsPerDay, і оновлюємо у ViewModel
    LaunchedEffect(wordsPerDay) {
        viewModel.resetPageIndex()
    }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    SetStatusBarColors(isDarkTheme = false)

    if (showContextMenu) {
        ContextMenuSheet(
            onNext = {
                viewModel.nextPage(wordsPerDay)
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
        WordCard(
            words = visibleWords,
            isFlipped = isFlipped,
            onFlip = { isFlipped = !isFlipped },
            modifier = Modifier
                .weight(1f)
                .then(AppModifiers.wordCardModifier)
        )
        BottomBar(
            AppModifiers.bottomBarModifier
        )
    }
}



