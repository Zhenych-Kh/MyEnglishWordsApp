package ua.ievetroy.myapplicationa.ui.screens.main.util

import android.content.Context
import ua.ievetroy.myapplicationa.data.preferences.words.WordOrderRepository
import ua.ievetroy.myapplicationa.data.repository.JsonWordRepository
import ua.ievetroy.myapplicationa.data.repository.WordRepository

fun provideWordRepository(context: Context): WordRepository =
    JsonWordRepository(context)

fun provideWordOrderRepository(context: Context): WordOrderRepository =
    WordOrderRepository(context)
