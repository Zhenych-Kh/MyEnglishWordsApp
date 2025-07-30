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
    val FIRST_VISIBLE_INDEX = intPreferencesKey("first_visible_index")
}

class WordOrderDataStore(private val context: Context) {
    suspend fun setWordOrder(order: List<Int>) {
        context.wordOrderDataStore.edit {
            it[WordOrderKeys.WORD_ORDER] = order.joinToString(",")
        }
    }

    suspend fun getWordOrder(): List<Int> {
        val raw = context.wordOrderDataStore.data
            .map { it[WordOrderKeys.WORD_ORDER] ?: "" }
            .firstOrNull() ?: ""
        return if (raw.isEmpty()) emptyList()
        else raw.split(",").mapNotNull { it.toIntOrNull() }
    }

    suspend fun setFirstVisibleIndex(index: Int) {
        context.wordOrderDataStore.edit {
            it[WordOrderKeys.FIRST_VISIBLE_INDEX] = index
        }
    }

    suspend fun getFirstVisibleIndex(): Int {
        return context.wordOrderDataStore.data
            .map { it[WordOrderKeys.FIRST_VISIBLE_INDEX] ?: 0 }
            .firstOrNull() ?: 0
    }
}

