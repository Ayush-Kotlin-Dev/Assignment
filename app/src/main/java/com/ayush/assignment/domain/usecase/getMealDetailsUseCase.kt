package com.ayush.assignment.domain.usecase

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.domain.model.MealDetail
import com.ayush.assignment.domain.repository.MealRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class GetMealDetailsUseCase(
    private val repository: MealRepository
) {
    operator fun invoke(id: String): Single<Result<MealDetail, DataError>> {
        return repository.getMealDetails(id)
    }
}