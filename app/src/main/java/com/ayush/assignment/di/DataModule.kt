package com.ayush.assignment.di

import com.ayush.assignment.core.data.NetworkModule
import com.ayush.assignment.data.remote.MealApi
import com.ayush.assignment.data.repository.MealRepositoryImpl
import com.ayush.assignment.domain.repository.MealRepository
import org.koin.dsl.module

val dataModule = module {
    single { NetworkModule.mealDbRetrofit.create(MealApi::class.java) }
    single<MealRepository> { MealRepositoryImpl(get()) }
}