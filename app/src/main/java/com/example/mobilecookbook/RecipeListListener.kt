package com.example.mobilecookbook

interface RecipeListListener {
    fun switchToRecipeDetailsFragment(data: Recipe)
    fun switchToAddRecipeFragment()
}