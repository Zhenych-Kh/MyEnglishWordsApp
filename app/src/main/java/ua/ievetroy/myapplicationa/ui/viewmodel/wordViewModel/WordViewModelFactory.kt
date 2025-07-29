package ua.ievetroy.myapplicationa.ui.viewmodel.wordViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.ievetroy.myapplicationa.data.repository.WordRepository
import ua.ievetroy.myapplicationa.data.preferences.words.WordOrderRepository

class WordViewModelFactory(
    private val repository: WordRepository,
    private val wordOrderRepository: WordOrderRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(repository, wordOrderRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
