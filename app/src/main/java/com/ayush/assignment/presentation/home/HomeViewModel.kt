package com.ayush.assignment.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.domain.usecase.GetCategoriesUseCase
import com.ayush.assignment.domain.usecase.GetMealsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getMealsUseCase: GetMealsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                categories = result.data,
                                isLoading = false,
                                error = null
                            )
                        }
                        // Load meals for first category if available
                        result.data.firstOrNull()?.let {
                            loadMealsForCategory(it.name)
                        }
                    }
                    is Result.Error -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = result.error
                            )
                        }
                    }
                }
            }
        }
    }

    fun loadMealsForCategory(category: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, selectedCategory = category) }

            // Add a small delay to make the loading state more visible
//            kotlinx.coroutines.delay(300) for testing

            getMealsUseCase(category).collect { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                meals = result.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is Result.Error -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = result.error
                            )
                        }
                    }
                }
            }
        }
    }

    fun retry() {
        loadCategories()
    }
}