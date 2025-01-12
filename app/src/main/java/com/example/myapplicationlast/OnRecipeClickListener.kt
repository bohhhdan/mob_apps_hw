package com.example.myapplicationlast

interface OnRecipeClickListener {
    fun onRecipeClick(recipeId: Int)
    fun onShareClick(recipeId: Int)
    fun onLikeClick(recipeId: Int)
}