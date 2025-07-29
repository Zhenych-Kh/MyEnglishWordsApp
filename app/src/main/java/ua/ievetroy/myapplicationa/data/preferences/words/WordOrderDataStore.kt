package ua.ievetroy.myapplicationa.data.preferences.words

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

val Context.wordOrderDataStore by preferencesDataStore(name = "word_order")

object WordOrderKeys {
    val WORD_ORDER = stringPreferencesKey("word_order")
    val PAGE_INDEX = intPreferencesKey("page_index")
}

class WordOrderDataStore(private val context: Context) {
    // Зберегти порядок (ID через кому)
    suspend fun setWordOrder(order: List<Int>) {
        context.wordOrderDataStore.edit {
            it[WordOrderKeys.WORD_ORDER] = order.joinToString(",")
        }
    }

    // Зчитати порядок
    suspend fun getWordOrder(): List<Int> {
        val raw = context.wordOrderDataStore.data
            .map { it[WordOrderKeys.WORD_ORDER] ?: "" }
            .firstOrNull() ?: ""
        return if (raw.isEmpty()) emptyList()
        else raw.split(",").mapNotNull { it.toIntOrNull() }
    }

    // --- Додаємо для індексу сторінки ---
    suspend fun setPageIndex(index: Int) {
        context.wordOrderDataStore.edit {
            it[WordOrderKeys.PAGE_INDEX] = index
        }
    }

    suspend fun getPageIndex(): Int {
        return context.wordOrderDataStore.data
            .map { it[WordOrderKeys.PAGE_INDEX] ?: 0 }
            .firstOrNull() ?: 0
    }
}
