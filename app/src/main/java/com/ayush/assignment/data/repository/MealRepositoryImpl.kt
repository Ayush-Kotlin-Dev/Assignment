package com.ayush.assignment.data.repository

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.data.mapper.toDomain
import com.ayush.assignment.data.remote.MealApi
import com.ayush.assignment.domain.model.Category
import com.ayush.assignment.domain.model.Meal
import com.ayush.assignment.domain.model.MealSummary
import com.ayush.assignment.domain.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

class MealRepositoryImpl(
    private val api: MealApi
) : MealRepository {

    override fun getRandomMeal(): Flow<Result<Meal, DataError>> = flow {
        try {
            val response = api.getRandomMeal()
            val meal = response.meals.firstOrNull()
            if (meal != null) {
                emit(Result.Success(meal.toDomain()))
            } else {
                emit(Result.Error(DataError.Remote.SERIALIZATION))
            }
        } catch (e: IOException) {
            emit(Result.Error(DataError.Remote.NO_INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(DataError.Remote.UNKNOWN))
        }
    }.flowOn(Dispatchers.IO)

    override fun getMealsByCategory(category: String): Flow<Result<List<MealSummary>, DataError>> = flow {
        try {
            val response = api.getMealsByCategory(category)
            val meals = response.meals.map { mealDto -> mealDto.toDomain() }
            emit(Result.Success(meals))
        } catch (e: IOException) {
            emit(Result.Error(DataError.Remote.NO_INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(DataError.Remote.UNKNOWN))
        }
    }.flowOn(Dispatchers.IO)

    override fun getCategories(): Flow<Result<List<Category>, DataError>> = flow {
        try {
            val response = api.getCategories()
            val categories = response.categories.map { categoryDto -> categoryDto.toDomain() }
            emit(Result.Success(categories))
        } catch (e: IOException) {
            emit(Result.Error(DataError.Remote.NO_INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(DataError.Remote.UNKNOWN))
        }
    }.flowOn(Dispatchers.IO)
}