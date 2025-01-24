package com.ayush.assignment.domain.model

data class MealDetail(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnailUrl: String,
    val youtubeUrl: String?,
    val ingredients: List<Ingredient>
) {
    data class Ingredient(
        val name: String,
        val measure: String
    )
}