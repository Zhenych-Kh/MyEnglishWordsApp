package ua.ievetroy.myapplicationa.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.ievetroy.myapplicationa.data.model.Word

class JsonWordRepository(private val context: Context) : WordRepository {
    private var cached: List<Word>? = null

    override suspend fun getAllWords(): List<Word> {
        return cached ?: loadFromAssets().also { cached = it }
    }

    private suspend fun loadFromAssets(): List<Word> = withContext(Dispatchers.IO) {
        val json = context.assets.open("words_sample.json")
            .bufferedReader(Charsets.UTF_8)
            .use { it.readText() }
        val type = object : TypeToken<List<Word>>() {}.type
        Gson().fromJson<List<Word>>(json, type)
    }

    override suspend fun getWordsByLevel(level: String): List<Word> =
        getAllWords().filter { it.levels.contains(level) }

    override suspend fun getWordsByTopic(topic: String): List<Word> =
        getAllWords().filter { it.topics.contains(topic) }
}
