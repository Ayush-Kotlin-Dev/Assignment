package com.ayush.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ayush.assignment.presentation.home.HomeScreen
import com.ayush.assignment.presentation.home.HomeViewModel
import com.ayush.assignment.ui.theme.AssignmentTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        enableEdgeToEdge()
        setContent {
            val viewModel = koinViewModel<HomeViewModel>()
            AssignmentTheme {
                val uiState by viewModel.uiState.collectAsState()

                HomeScreen(
                    uiState = uiState,
                    onCategorySelected = viewModel::loadMealsForCategory,
                    onRetry = viewModel::retry,
                    onMealClick = { mealId ->
                        // We'll implement navigation to detail screen later
                    }
                )
            }
        }
    }
}

