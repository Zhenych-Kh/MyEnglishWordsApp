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

    // Зберігаємо індекс поточної картки (через StateFlow — щоб працювало з Compose)
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage
    private var wordsPerPage: Int = 5

    fun resetPageIndex() {
        viewModelScope.launch {
            wordOrderRepository.setPageIndex(0)
            _currentPage.value = 0
        }
    }

    fun setWordsPerPage(count: Int) {
        wordsPerPage = count
        // Перевіряємо чи не виліз індекс за межу
        val pageCount = getPageCount()
        if (_currentPage.value >= pageCount) {
            _currentPage.value = 0
        }
        updateVisibleWords()
    }

    fun init() {
        viewModelScope.launch {
            val allWords = repository.getAllWords()
            val order = wordOrderRepository.getWordOrder()
            val page = wordOrderRepository.getPageIndex()
            orderedWords = if (order.isNotEmpty()) {
                order.mapNotNull { id -> allWords.find { it.id == id } }
            } else {
                val shuffled = allWords.shuffled()
                wordOrderRepository.setWordOrder(shuffled.map { it.id })
                shuffled
            }
            _currentPage.value = page
            _words.value = orderedWords
        }
    }


    // Метод для отримання кількості "карток"
    fun getPageCount(): Int = (orderedWords.size + wordsPerPage - 1) / wordsPerPage

    // Наступна картка
    fun nextPage(wordsPerPage: Int) {
        viewModelScope.launch {
            val pageCount = (orderedWords.size + wordsPerPage - 1) / wordsPerPage
            val next = if (_currentPage.value < pageCount - 1) _currentPage.value + 1 else 0
            _currentPage.value = next
            wordOrderRepository.setPageIndex(next)
            // Якщо потрібно — онови _words.value тут, або використай words.take(...)
        }
    }

    // Оновлюємо видимі слова відповідно до сторінки/картки
    private fun updateVisibleWords() {
        val page = _currentPage.value
        val start = page * wordsPerPage
        _words.value = orderedWords.drop(start).take(wordsPerPage)
    }

    // Для “Оновити слова”/”Новий набір”
    fun reshuffleWords() {
        viewModelScope.launch {
            val allWords = repository.getAllWords()
            val shuffled = allWords.shuffled()
            wordOrderRepository.setWordOrder(shuffled.map { it.id })
            orderedWords = shuffled
            _currentPage.value = 0
            updateVisibleWords()
        }
    }
}

