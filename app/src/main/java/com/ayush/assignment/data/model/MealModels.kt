package com.ayush.assignment.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponse(
    @SerialName("meals")
    val meals: List<MealDto>
)

@Serializable
data class MealListResponse(
    @SerialName("meals")
    val meals: List<MealSummaryDto>
)

@Serializable
data class CategoryResponse(
    @SerialName("categories")
    val categories: List<CategoryDto>
)

@Serializable
data class MealDto(
    @SerialName("idMeal")
    val id: String,
    @SerialName("strMeal")
    val name: String,
    @SerialName("strCategory")
    val category: String,
    @SerialName("strArea")
    val area: String,
    @SerialName("strInstructions")
    val instructions: String,
    @SerialName("strMealThumb")
    val thumbnailUrl: String,
    @SerialName("strYoutube")
    val youtubeUrl: String? = null,
    @SerialName("strIngredient1") val ingredient1: String? = null,
    @SerialName("strIngredient2") val ingredient2: String? = null,
    @SerialName("strIngredient3") val ingredient3: String? = null,
    @SerialName("strIngredient4") val ingredient4: String? = null,
    @SerialName("strIngredient5") val ingredient5: String? = null,
    @SerialName("strMeasure1") val measure1: String? = null,
    @SerialName("strMeasure2") val measure2: String? = null,
    @SerialName("strMeasure3") val measure3: String? = null,
    @SerialName("strMeasure4") val measure4: String? = null,
    @SerialName("strMeasure5") val measure5: String? = null,
)

@Serializable
data class MealSummaryDto(
    @SerialName("idMeal")
    val id: String,
    @SerialName("strMeal")
    val name: String,
    @SerialName("strMealThumb")
    val thumbnailUrl: String,
)

@Serializable
data class CategoryDto(
    @SerialName("idCategory")
    val id: String,
    @SerialName("strCategory")
    val name: String,
    @SerialName("strCategoryThumb")
    val thumbnailUrl: String,
    @SerialName("strCategoryDescription")
    val description: String
)