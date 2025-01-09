package com.example.myapplicationlast

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val listener: OnRecipeClickListener
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeNameTextView: TextView = itemView.findViewById(R.id.textViewName)
        val recipeDescriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        val recipeImageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_layout, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeNameTextView.text = recipe.name
        holder.recipeDescriptionTextView.text = recipe.description
        holder.recipeImageView.setImageResource(recipe.photoResId)

        // Add click listener to the item view
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RecipeDetailsActivity::class.java)
            intent.putExtra("recipe_id", recipe.id)
            holder.itemView.context.startActivity(intent)
        }

        val share: ImageButton = holder.itemView.findViewById(R.id.buttonShare)

        share.setOnClickListener {
            listener.onShareClick(recipe.id)
        }

        val like: ImageButton = holder.itemView.findViewById(R.id.buttonLike)

        like.setOnClickListener {
            listener.onLikeClick(recipe.id)
        }

    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}
