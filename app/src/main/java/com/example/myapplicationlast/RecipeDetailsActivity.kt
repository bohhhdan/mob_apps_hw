package com.example.myapplicationlast

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        // Initialize views
        imageView = findViewById(R.id.imageViewDetail)
        textViewName = findViewById(R.id.textViewDetailName)
        textViewDescription = findViewById(R.id.textViewDetailDescription)


        val recipeName = intent.getStringExtra("recipe_name")
        val recipeDescription = intent.getStringExtra("recipe_description")
        val recipeImage = intent.getIntExtra("recipe_image", R.drawable.food1)


        textViewName.text = recipeName
        textViewDescription.text = recipeDescription
        imageView.setImageResource(recipeImage)
    }
}
