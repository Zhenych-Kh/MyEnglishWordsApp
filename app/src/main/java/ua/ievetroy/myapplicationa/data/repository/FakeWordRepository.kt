package ua.ievetroy.myapplicationa.data.repository

import ua.ievetroy.myapplicationa.data.model.Word
import ua.ievetroy.myapplicationa.data.dummy.sampleWords

class FakeWordRepository : WordRepository {
    override suspend fun getAllWords(): List<Word> = sampleWords

    override suspend fun getWordsByLevel(level: String): List<Word> =
        sampleWords.filter { it.levels.contains(level) }

    override suspend fun getWordsByTopic(topic: String): List<Word> =
        sampleWords.filter { it.topics.contains(topic) }
}
