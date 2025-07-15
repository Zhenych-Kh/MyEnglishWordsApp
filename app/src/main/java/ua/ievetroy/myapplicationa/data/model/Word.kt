package ua.ievetroy.myapplicationa.data.model

data class Word(
    val word: String,
    val translation: String,
    val example: String,
    var isExpanded: Boolean = false
)


