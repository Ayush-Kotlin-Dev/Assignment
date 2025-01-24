package com.ayush.assignment.data.mapper

import com.ayush.assignment.data.model.CategoryDto
import com.ayush.assignment.data.model.MealDto
import com.ayush.assignment.data.model.MealSummaryDto
import com.ayush.assignment.domain.model.Category
import com.ayush.assignment.domain.model.Meal
import com.ayush.assignment.domain.model.MealSummary

fun MealDto.toDomain() = Meal(
    id = id,
    name = name,
    category = category,
    area = area,
    instructions = instructions,
    thumbnailUrl = thumbnailUrl
)

fun MealSummaryDto.toDomain() = MealSummary(
    id = id,
    name = name,
    thumbnailUrl = thumbnailUrl
)

fun CategoryDto.toDomain() = Category(
    id = id,
    name = name,
    thumbnailUrl = thumbnailUrl,
    description = description
)