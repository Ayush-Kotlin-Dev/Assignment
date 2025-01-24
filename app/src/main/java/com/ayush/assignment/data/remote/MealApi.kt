package com.ayush.assignment.data.remote

import com.ayush.assignment.data.model.CategoryResponse
import com.ayush.assignment.data.model.MealListResponse
import com.ayush.assignment.data.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealListResponse

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") id: String): MealResponse
}