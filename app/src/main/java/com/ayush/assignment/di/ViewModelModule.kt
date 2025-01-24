package com.ayush.assignment.di

import com.ayush.assignment.presentation.detail.MealDetailViewModel
import com.ayush.assignment.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { parameters ->
        MealDetailViewModel(get(), parameters.get())
    }
}