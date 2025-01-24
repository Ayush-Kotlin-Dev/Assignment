package com.ayush.assignment.di

import com.ayush.assignment.domain.usecase.GetMealDetailsUseCase
import com.ayush.assignment.domain.usecase.GetMealsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetMealsUseCase(get()) }
    factory { GetMealDetailsUseCase(get()) }

}