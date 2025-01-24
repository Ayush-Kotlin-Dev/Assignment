package com.ayush.assignment.domain.repository

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.domain.model.Category
import com.ayush.assignment.domain.model.Meal
import com.ayush.assignment.domain.model.MealSummary
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    fun getRandomMeal(): Flow<Result<Meal, DataError>>
    fun getMealsByCategory(category: String): Flow<Result<List<MealSummary>, DataError>>
    fun getCategories(): Flow<Result<List<Category>, DataError>>
}