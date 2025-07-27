package ua.ievetroy.myapplicationa.data.model

data class Word(
    val id: Int,
    val word: String,
    val translation: String,
    val example: String,
    val levels: List<String>,   // ← ОБОВ’ЯЗКОВО!
    val topics: List<String>    // ← ОБОВ’ЯЗКОВО!
)



