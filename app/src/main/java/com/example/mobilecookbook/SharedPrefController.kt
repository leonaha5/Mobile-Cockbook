package com.example.mobilecookbook

import android.content.Context
import com.google.gson.Gson

fun saveRecipeList(context: Context, list: MutableList<Recipe>) {
    val editor = context.getSharedPreferences("Recipes", Context.MODE_PRIVATE).edit()
    editor.putString("recipeList", Gson().toJson(list))
    editor.apply()
}

fun getRecipeList(context: Context): MutableList<Recipe> {
    val json =
        context.getSharedPreferences("Recipes", Context.MODE_PRIVATE).getString("recipeList", null)
    return if (json != null) {
        Gson().fromJson(json, Array<Recipe>::class.java).toMutableList()
    } else {
        mutableListOf()
    }
}

fun addRecipe(context: Context, recipe: Recipe) {
    val list = getRecipeList(context)
    list.add(0, recipe)
    saveRecipeList(context, list)
}

fun editRecipe(context: Context, recipe: Recipe, index: Int) {
    val list = getRecipeList(context)
    list[index] = recipe
    saveRecipeList(context, list)
}

fun deleteRecipe(context: Context, index: Int) {
    val list = getRecipeList(context)
    list.removeAt(index)
    saveRecipeList(context, list)
}
