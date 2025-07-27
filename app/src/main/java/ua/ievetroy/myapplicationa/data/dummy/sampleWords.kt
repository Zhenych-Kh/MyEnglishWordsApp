package ua.ievetroy.myapplicationa.data.dummy

import ua.ievetroy.myapplicationa.data.model.Word

val sampleWords: List<Word> = listOf(
    Word(
        id = 1,
        word = "appleууу",
        translation = "яблуко",
        example = "I eat an apple every day.",
        levels = listOf("A1"),
        topics = listOf("Food", "Fruits")
    ),
    Word(
        id = 2,
        word = "learn",
        translation = "вчити",
        example = "She can run very fast.",
        levels = listOf("A1"),
        topics = listOf("Education", "Actions")
    ),
    Word(
        id = 3,
        word = "run",
        translation = "бігти",
        example = "She can run very fast.",
        levels = listOf("A1"),
        topics = listOf("Actions", "Sports")
    ),
    Word(
        id = 4,
        word = "broke",
        translation = "зламався",
        example = "My phone broke yesterday.",
        levels = listOf("B1"),
        topics = listOf("Daily life")
    ),
    Word(
        id = 5,
        word = "acknowledgement",
        translation = "підтвердження",
        example = "He nodded in acknowledgement.",
        levels = listOf("B2"),
        topics = listOf("Communication")
    )
)
