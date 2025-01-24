package com.ayush.assignment.data.repository

import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.data.mapper.toDetailDomain
import com.ayush.assignment.data.mapper.toDomain
import com.ayush.assignment.data.remote.MealApi
import com.ayush.assignment.domain.model.Category
import com.ayush.assignment.domain.model.Meal
import com.ayush.assignment.domain.model.MealDetail
import com.ayush.assignment.domain.model.MealSummary
import com.ayush.assignment.domain.repository.MealRepository
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class MealRepositoryImpl(
    private val api: MealApi
) : MealRepository {


    override fun getMealsByCategory(category: String): Single<Result<List<MealSummary>, DataError>> {
        return wrapApiCall(
            api.getMealsByCategory(category)
                .map { response -> response.meals.map { it.toDomain() } }
        )
    }


    override fun getMealDetails(id: String): Single<Result<MealDetail, DataError>> {
        return wrapApiCall(
            api.getMealDetails(id)
                .map { response ->
                    response.meals.firstOrNull()?.toDetailDomain() 
                        ?: throw IOException("No meal found")
                }
        )
    }

    private fun <T : Any> wrapApiCall(apiCall: Single<T>): Single<Result<T, DataError>> {
        return apiCall
            .map<Result<T, DataError>> { Result.Success(it) }
            .onErrorReturn { error ->
                when (error) {
                    is IOException -> Result.Error(DataError.Remote.NO_INTERNET)
                    else -> Result.Error(DataError.Remote.UNKNOWN)
                }
            }
    }
}