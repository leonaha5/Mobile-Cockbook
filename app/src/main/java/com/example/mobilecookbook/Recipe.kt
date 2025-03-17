package com.example.mobilecookbook

class Recipe(
    val name: String,
    val type: String,
    val prepTime: Int,
    val rating: Int? = null,
    val ingredients: MutableList<String>? = null,
    val steps: MutableList<String>? = null,
)