package com.example.myapplicationlast

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val recipeId = intent.getIntExtra("recipe_id", -1)
        if (recipeId != -1) {
            val recipe = getRecipeById(recipeId)
            if (recipe != null) {
                val recipeNameTextView: TextView = findViewById(R.id.textViewDetailName)
                val recipeDescriptionTextView: TextView = findViewById(R.id.textViewDetailDescription)
                val recipeImageView: ImageView = findViewById(R.id.imageViewDetail)

                recipeNameTextView.text = recipe.name
                recipeDescriptionTextView.text = recipe.description
                recipeImageView.setImageResource(recipe.photoResId)
            }
        }
    }

    private fun getRecipeById(recipeId: Int): Recipe? {
        val recipes = listOf(
            Recipe(1, "Pizza", "Delicious pizza with cheese and toppings", R.drawable.food1),
            Recipe(2, "Pasta", "Creamy pasta with vegetables", R.drawable.food3),

        )
        return recipes.find { it.id == recipeId }
    }
}