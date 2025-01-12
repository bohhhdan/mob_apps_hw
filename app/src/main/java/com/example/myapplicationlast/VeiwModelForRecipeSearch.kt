package com.example.myapplicationlast

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VeiwModelForRecipeSearch : ViewModel() {

    private val currentRecipesFlow = MutableStateFlow<List<Recipe>>(listOf())
    val visibleRecipesFlow: StateFlow<List<Recipe>> = currentRecipesFlow

    private val fullRecipeList = listOf(
        Recipe(1, "Pizza", "Tasty pizza with cheese", R.drawable.food1),
        Recipe(2, "Pasta", "Yummy creamy pasta", R.drawable.food3),
        Recipe(3, "Burger", "Delicious and juicy burger", R.drawable.food2)
    )

    init {
        currentRecipesFlow.value = fullRecipeList
    }

    fun filterRecipesBasedOnQuery(query: String) {
        if (query.length < 3) {
            currentRecipesFlow.value = fullRecipeList
            return
        }

        val matchingRecipes = mutableListOf<Recipe>()

        for (recipe in fullRecipeList) {

            if (recipe.name.contains(query, ignoreCase = true) ||
                recipe.description.contains(query, ignoreCase = true)) {
                matchingRecipes.add(recipe)
            }
        }


        currentRecipesFlow.value = matchingRecipes
    }

}
