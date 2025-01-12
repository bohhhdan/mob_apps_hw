package com.example.myapplicationlast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val listener: OnRecipeClickListener
) : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

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
        val recipe = getItem(position)
        holder.recipeNameTextView.text = recipe.name
        holder.recipeDescriptionTextView.text = recipe.description
        holder.recipeImageView.setImageResource(recipe.photoResId)

        holder.itemView.setOnClickListener {
            listener.onRecipeClick(recipe.id)
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

    private class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    fun RecipesUpdater(newRecipeList: List<Recipe>) {
        submitList(newRecipeList)
    }
}
