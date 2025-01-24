package com.ayush.assignment.domain.model


data class Meal(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnailUrl: String,
)

data class MealSummary(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
)

data class Category(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val description: String
)