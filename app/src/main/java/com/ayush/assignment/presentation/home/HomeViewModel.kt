// HomeViewModel.kt
package com.ayush.assignment.presentation.home

import androidx.lifecycle.ViewModel
import com.ayush.assignment.core.domain.DataError
import com.ayush.assignment.core.domain.DataError.Remote
import com.ayush.assignment.core.domain.Result
import com.ayush.assignment.domain.model.MealSummary
import com.ayush.assignment.domain.usecase.GetMealsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val getMealsUseCase: GetMealsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    private val compositeDisposable = CompositeDisposable()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        _uiState.update { it.copy(isLoading = true) }

        // Fetch two different categories simultaneously
        Single.zip(
            getMealsUseCase("Seafood"),
            getMealsUseCase("Dessert")
        ) { seafoodResult, dessertResult ->
            when {
                seafoodResult is Result.Success && dessertResult is Result.Success -> {
                    MealDataResult.Success(
                        seafoodMeals = seafoodResult.data,
                        dessertMeals = dessertResult.data
                    )
                }
                seafoodResult is Result.Error -> {
                    MealDataResult.Error(seafoodResult.error)
                }
                dessertResult is Result.Error -> {
                    MealDataResult.Error(dessertResult.error)
                }
                else -> MealDataResult.Error(Remote.UNKNOWN)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                when (result) {
                    is MealDataResult.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                seafoodMeals = result.seafoodMeals,
                                dessertMeals = result.dessertMeals,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is MealDataResult.Error -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = result.error
                            )
                        }
                    }
                }
            }, { error ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = Remote.UNKNOWN
                    )
                }
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun retry() {
        loadInitialData()
    }

    fun onTabSelected(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
    }

    sealed class MealDataResult {
        data class Success(
            val seafoodMeals: List<MealSummary>,
            val dessertMeals: List<MealSummary>
        ) : MealDataResult()

        data class Error(val error: DataError) : MealDataResult()
    }
}
