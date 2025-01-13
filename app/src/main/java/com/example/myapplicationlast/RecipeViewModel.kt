package com.example.myapplicationlast

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeViewModel: ViewModel() {
    private val recipes = listOf(
        Recipe(1, "Pizza", "pizza desc", R.drawable.food1),
        Recipe(2, "Pasta", "pasta desc", R.drawable.food2),
    )

    private val _filteredRecipes = MutableStateFlow(recipes)
    val filteredRecipes: StateFlow<List<Recipe>> = _filteredRecipes.asStateFlow()

    fun filterRecipes(query: String) {
        if (query.isEmpty() || query.length < 3) {
            _filteredRecipes.value = recipes
            return
        } else {
            _filteredRecipes.value = recipes.filter { it.name.contains(query, ignoreCase = true) }
        }
    }
}