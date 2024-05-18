package com.example.newsapp.feature.categories.presentation.model

import com.example.newsapp.feature.news.data.model.Category

data class CategoryUiModel(
    val name: String,
    val codeName: String,
    //res?
    //val iconId: Int,
)

fun Category.toUi() =
    CategoryUiModel(name, codeName)
