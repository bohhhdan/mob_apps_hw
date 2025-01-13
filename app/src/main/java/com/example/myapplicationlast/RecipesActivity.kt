package com.example.myapplicationlast

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipesActivity : AppCompatActivity(), OnRecipeClickListener {

    private val viewModel : RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val searchView : SearchView = findViewById(R.id.searchView)

        val recipes = listOf(Recipe(1, "Pizza", "pizza desc", R.drawable.food1), Recipe(2, "Pasta","pastaaa desc", R.drawable.food3))
        val adapter = RecipeAdapter(recipes, this)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                viewModel.filterRecipes(query.toString())
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

        })
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