package com.ayush.assignment.data.remote

import com.ayush.assignment.data.model.CategoryResponse
import com.ayush.assignment.data.model.MealListResponse
import com.ayush.assignment.data.model.MealResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") category: String): Single<MealListResponse>

    @GET("lookup.php")
    fun getMealDetails(@Query("i") id: String): Single<MealResponse>
}