package com.example.myapplicationlast

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class RecipesActivity : AppCompatActivity(), OnRecipeClickListener {

    private val viewModel: VeiwModelForRecipeSearch by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        setupRecyclerView()
        setupSearchView()
        UpdaterForRecipe()
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter(this)
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@RecipesActivity)
            adapter = recipeAdapter
        }
    }

    private fun setupSearchView() {
        findViewById<SearchView>(R.id.searchView).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { viewModel.filterRecipesBasedOnQuery(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { viewModel.filterRecipesBasedOnQuery(it) }
                    return true
                }
            })
        }
    }

    private fun UpdaterForRecipe() {
        lifecycleScope.launch {
            viewModel.visibleRecipesFlow.collect { recipes ->
                if (recipes.isEmpty()) {
                    Toast.makeText(this@RecipesActivity, "No recipes found", Toast.LENGTH_SHORT)
                        .show()
                }
                recipeAdapter.RecipesUpdater(recipes)
            }
        }
    }

    override fun onRecipeClick(recipeId: Int) {
        Toast.makeText(this, "Recipe clicked: $recipeId", Toast.LENGTH_SHORT).show()

        Intent(this, RecipeDetailsActivity::class.java).apply {
            putExtra("recipe_id", recipeId)
            startActivity(this)
        }
    }
}