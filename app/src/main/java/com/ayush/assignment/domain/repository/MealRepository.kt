package com.ayush.assignment.domain.repository

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.domain.model.Category
import com.ayush.assignment.domain.model.Meal
import com.ayush.assignment.domain.model.MealDetail
import com.ayush.assignment.domain.model.MealSummary
import io.reactivex.rxjava3.core.Single

interface MealRepository {
    fun getMealsByCategory(category: String): Single<Result<List<MealSummary>, DataError>>
    fun getMealDetails(id: String): Single<Result<MealDetail, DataError>>
}