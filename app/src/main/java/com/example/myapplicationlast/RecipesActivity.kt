package com.example.myapplicationlast

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class RecipesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipeList = listOf(
        Recipe(1, "Product 1", "Description of Product 1", R.drawable.food1),
        Recipe(2, "Product 2", "Description of Product 2", R.drawable.food2),
        Recipe(3, "Product 3", "Description of Product 3", R.drawable.food3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(recipeList, object : RecipeAdapter.OnRecipeInteractionListener {
            override fun onRecipeClick(recipe: Recipe) {
                // Pass the recipe details to RecipeDetailsActivity
                Toast.makeText(this@RecipesActivity, "Clicked: ${recipe.name}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RecipesActivity, RecipeDetailsActivity::class.java)
                intent.putExtra("recipe_id", recipe.id)
                intent.putExtra("recipe_name", recipe.name)
                intent.putExtra("recipe_description", recipe.description)
                intent.putExtra("recipe_image", recipe.imageResource) // Pass image resource
                startActivity(intent)
            }

            override fun onLikeClick(recipe: Recipe) {
                Toast.makeText(this@RecipesActivity, "Liked: ${recipe.name}", Toast.LENGTH_SHORT).show()
            }

            override fun onShareClick(recipe: Recipe) {
                Toast.makeText(this@RecipesActivity, "Shared: ${recipe.name}", Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView.adapter = recipeAdapter
    }
}
