package com.example.myapplicationlast

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipesActivity : AppCompatActivity(), OnRecipeClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recipes = listOf(Recipe(1, "Pizza", "pizza desc", R.drawable.food1), Recipe(2, "Pasta","pastaaa desc", R.drawable.food3))
        val adapter = RecipeAdapter(recipes, this)
        recyclerView.adapter = adapter
    }

    override fun onShareClick(recipeId: Int) {
        Toast.makeText(this, "Share button of recipe clicked: $recipeId", Toast.LENGTH_SHORT).show()
    }

    override fun onLikeClick(recipeId: Int) {
        Toast.makeText(this, "Like button of recipe clicked: $recipeId", Toast.LENGTH_SHORT).show()
    }
    override fun onRecipeClick(recipeId: Int) {
        Toast.makeText(this, "Recipe clicked: $recipeId", Toast.LENGTH_SHORT).show()
    }

}