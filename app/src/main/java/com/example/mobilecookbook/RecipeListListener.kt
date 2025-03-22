package com.example.mobilecookbook

interface RecipeListListener {
    fun switchToRecipeDetailsFragment(data: Recipe, index: Int)
    fun switchToEditRecipeDetailsFragment(data: Recipe, index: Int)
    fun switchToAddRecipeFragment()
    fun switchToRecipeListFragment()
}