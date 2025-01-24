package com.ayush.assignment.di

import com.ayush.assignment.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // ViewModels will be added here later
    viewModel { HomeViewModel(get(), get()) }

}