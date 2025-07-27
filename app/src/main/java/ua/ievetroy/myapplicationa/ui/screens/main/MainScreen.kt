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
    settingsViewModel: SettingsViewModel   // ← без дефолтного значення!
) {
    val context = LocalContext.current
    val wordRepository: WordRepository = JsonWordRepository(context)
    val viewModel: WordViewModel = viewModel(
        factory = WordViewModelFactory(wordRepository)
    )

    // Завантажити слова лише раз
    LaunchedEffect(Unit) {
        viewModel.loadAllWords()
    }

    val wordsPerDay by settingsViewModel.wordsPerDay.collectAsState()
    val words by viewModel.words.collectAsState()

    var isFlipped by remember { mutableStateOf(false) }
    var showContextMenu by remember { mutableStateOf(false) }

    SetStatusBarColors(isDarkTheme = false)

    if (showContextMenu) {
        ContextMenuSheet(
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
            words = words.take(wordsPerDay),                  // ← передаємо список слів сюди
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

