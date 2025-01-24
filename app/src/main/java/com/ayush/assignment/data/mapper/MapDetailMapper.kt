package com.ayush.assignment.data.mapper

import android.R.id
import com.ayush.assignment.data.model.MealDto
import com.ayush.assignment.domain.model.MealDetail
import com.ayush.assignment.domain.model.MealDetail.Ingredient
import kotlin.collections.listOfNotNull

fun MealDto.toDetailDomain(): MealDetail {
    val ingredients = listOfNotNull(
        ingredient1?.takeIf { it.isNotBlank() }?.let {
            Ingredient(it, measure1 ?: "")
        },
        ingredient2?.takeIf { it.isNotBlank() }?.let {
            Ingredient(it, measure2 ?: "")
        },
        ingredient3?.takeIf { it.isNotBlank() }?.let {
            Ingredient(it, measure3 ?: "")
        },
        ingredient4?.takeIf { it.isNotBlank() }?.let {
            Ingredient(it, measure4 ?: "")
        },
        ingredient5?.takeIf { it.isNotBlank() }?.let {
            Ingredient(it, measure5 ?: "")
        }
    )

    return MealDetail(
        id = id,
        name = name,
        category = category,
        area = area,
        instructions = instructions,
        thumbnailUrl = thumbnailUrl,
        youtubeUrl = youtubeUrl,
        ingredients = ingredients
    )
}