package ua.ievetroy.myapplicationa.ui.viewmodel.wordViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.data.repository.WordRepository

class WordViewModel(private val repository: WordRepository) : ViewModel() {
    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words: StateFlow<List<Word>> = _words

    fun loadAllWords() {
        viewModelScope.launch {
            _words.value = repository.getAllWords()
        }
    }

    fun loadWordsByLevel(level: String) {
        viewModelScope.launch {
            _words.value = repository.getWordsByLevel(level)
        }
    }
}

class WordViewModelFactory(
    private val repository: WordRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
