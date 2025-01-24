package com.ayush.assignment.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.domain.usecase.GetMealDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MealDetailViewModel(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mealId: String = checkNotNull(savedStateHandle["mealId"])

    private val _uiState = MutableStateFlow(MealDetailUiState(isLoading = true))
    val uiState: StateFlow<MealDetailUiState> = _uiState.asStateFlow()

    init {
        loadMealDetails()
    }

    private fun loadMealDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            getMealDetailsUseCase(mealId)
                .subscribe({ result ->
                    when (result) {
                        is Result.Success -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    error = null,
                                    meal = result.data
                                )
                            }
                        }
                        is Result.Error -> {
                            _uiState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    error = result.error,
                                    meal = null
                                )
                            }
                        }
                    }
                })
        }
    }

    fun retry() {
        loadMealDetails()
    }
}