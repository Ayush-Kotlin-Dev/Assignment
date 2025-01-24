package com.ayush.assignment.domain.usecase

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.domain.model.Category
import com.ayush.assignment.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val repository: MealRepository
) {
    operator fun invoke(): Flow<Result<List<Category>, DataError>> {
        return repository.getCategories()
    }
}