package com.ayush.assignment.presentation.home

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.domain.model.Category
import com.ayush.assignment.domain.model.MealSummary

data class HomeUiState(
    val categories: List<Category> = emptyList(),
    val meals: List<MealSummary> = emptyList(),
    val selectedCategory: String = "",
    val isLoading: Boolean = false,
    val error: DataError? = null
)