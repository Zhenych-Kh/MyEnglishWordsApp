package ua.ievetroy.myapplicationa.data.preferences.words

import android.content.Context

class WordOrderRepository(context: Context) {
    private val dataStore = WordOrderDataStore(context)

    suspend fun setWordOrder(order: List<Int>) = dataStore.setWordOrder(order)
    suspend fun getWordOrder(): List<Int> = dataStore.getWordOrder()
    suspend fun setFirstVisibleIndex(index: Int) = dataStore.setFirstVisibleIndex(index)
    suspend fun getFirstVisibleIndex(): Int = dataStore.getFirstVisibleIndex()
}


