package com.ayush.assignment.di

import com.ayush.assignment.domain.usecase.GetCategoriesUseCase
import com.ayush.assignment.domain.usecase.GetMealDetailsUseCase
import com.ayush.assignment.domain.usecase.GetMealsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCategoriesUseCase(get()) }
    factory { GetMealsUseCase(get()) }
    factory { GetMealDetailsUseCase(get()) }

}