package com.ayush.assignment.presentation.detail

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.domain.model.MealDetail

data class MealDetailUiState(
    val meal: MealDetail? = null,
    val isLoading: Boolean = false,
    val error: DataError? = null
)