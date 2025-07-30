package ua.ievetroy.myapplicationa.ui.viewmodel.wordViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.data.repository.WordRepository
import ua.ievetroy.myapplicationa.data.preferences.words.WordOrderRepository

class WordViewModel(
    private val repository: WordRepository,
    private val wordOrderRepository: WordOrderRepository
) : ViewModel() {

    private var orderedWords: List<Word> = emptyList()
    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words: StateFlow<List<Word>> = _words

    private val _firstVisibleIndex = MutableStateFlow(0)
    val firstVisibleIndex: StateFlow<Int> = _firstVisibleIndex

    fun init() {
        viewModelScope.launch {
            val allWords = repository.getAllWords()
            val order = wordOrderRepository.getWordOrder()
            val index = wordOrderRepository.getFirstVisibleIndex()
            orderedWords = if (order.isNotEmpty()) {
                order.mapNotNull { id -> allWords.find { it.id == id } }
            } else {
                val shuffled = allWords.shuffled()
                wordOrderRepository.setWordOrder(shuffled.map { it.id })
                shuffled
            }
            _words.value = orderedWords
            _firstVisibleIndex.value = index.coerceIn(0, (orderedWords.size - 1).coerceAtLeast(0))
        }
    }

    fun nextCard(wordsPerDay: Int) {
        viewModelScope.launch {
            val maxIndex = ((orderedWords.size - 1).coerceAtLeast(0))
            var next = _firstVisibleIndex.value + wordsPerDay
            if (next > maxIndex) next = 0 // циклічно
            _firstVisibleIndex.value = next
            wordOrderRepository.setFirstVisibleIndex(next)
        }
    }

    fun setFirstVisibleIndex(index: Int) {
        viewModelScope.launch {
            val safe = index.coerceIn(0, (orderedWords.size - 1).coerceAtLeast(0))
            _firstVisibleIndex.value = safe
            wordOrderRepository.setFirstVisibleIndex(safe)
        }
    }
}




