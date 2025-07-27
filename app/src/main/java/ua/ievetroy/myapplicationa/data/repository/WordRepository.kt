package ua.ievetroy.myapplicationa.data.repository

import ua.ievetroy.myapplicationa.data.model.Word

interface WordRepository {
    suspend fun getAllWords(): List<Word>
    suspend fun getWordsByLevel(level: String): List<Word>
    suspend fun getWordsByTopic(topic: String): List<Word>
}
