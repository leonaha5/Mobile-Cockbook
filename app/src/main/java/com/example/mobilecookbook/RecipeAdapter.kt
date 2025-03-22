package com.example.mobilecookbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val context: Context,
    private val recipeList: MutableList<Recipe>,
    private val openRecipeDetails: (Recipe, Int) -> Unit,
) : RecyclerView.Adapter<RecipeAdapter.ItemViewHolder>() {
    class ItemViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        val recipeIV: ImageView = itemView.findViewById(R.id.recipeIV)
        val recipeNameTV: TextView = itemView.findViewById(R.id.recipeNameTV)
        val dishTypeTV: TextView = itemView.findViewById(R.id.recipeDishTypeTV)
        val prepTimeTV: TextView = itemView.findViewById(R.id.recipePrepTimeTV)
        val recipeDeleteIB: ImageView = itemView.findViewById(R.id.recipeDeleteIB)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemViewHolder {
        val itemView =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recipe, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
    ) {
        val currentRecipe = recipeList[position]

        holder.recipeNameTV.text = currentRecipe.name
        holder.dishTypeTV.text = currentRecipe.type
        holder.prepTimeTV.text = currentRecipe.prepTime.toString()

        holder.recipeIV.setOnClickListener {
            openRecipeDetails(currentRecipe, position)
        }

        holder.recipeDeleteIB.setOnClickListener {
            deleteRecipe(context, position)
            recipeList.removeAt(position)
            notifyItemRemoved(position)
        }


    }

    override fun getItemCount(): Int = recipeList.size
}