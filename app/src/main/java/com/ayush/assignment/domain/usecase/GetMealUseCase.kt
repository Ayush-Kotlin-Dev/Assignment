package com.ayush.assignment.domain.usecase

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.domain.model.Category
import com.ayush.assignment.domain.model.MealSummary
import com.ayush.assignment.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetMealsUseCase(
    private val repository: MealRepository
) {
    operator fun invoke(category: String): Flow<Result<List<MealSummary>, DataError>> {
        return repository.getMealsByCategory(category)
    }
}