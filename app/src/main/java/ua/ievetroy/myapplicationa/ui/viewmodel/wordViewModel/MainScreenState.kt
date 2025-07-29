package ua.ievetroy.myapplicationa.ui.viewmodel.wordViewModel

import ua.ievetroy.myapplicationa.data.model.Word

// ui/screens/main/MainScreenState.kt
data class MainScreenState(
    val wordsPerDay: Int,
    val words: List<Word>,
    val currentPage: Int,
    val isFlipped: Boolean = false,
    val showContextMenu: Boolean = false,
    val visibleWords: List<Word> = emptyList()
)

