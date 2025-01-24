package com.ayush.assignment.presentation.home

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.domain.model.MealSummary

data class HomeUiState(
    val seafoodMeals: List<MealSummary> = emptyList(),
    val dessertMeals: List<MealSummary> = emptyList(),
    var selectedTab: Int = 0,
    val isLoading: Boolean = false,
    val error: DataError? = null
)