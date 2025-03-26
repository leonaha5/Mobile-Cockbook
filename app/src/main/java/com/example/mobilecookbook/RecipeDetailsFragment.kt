package com.example.mobilecookbook

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson

class RecipeDetailsFragment : Fragment() {
    private lateinit var listener: RecipeListListener


    override fun onStart() {
        super.onStart()
        super.onStop()
        Log.d(tag, "onStop: Activity is no longer visible to the user")
    }

    override fun onResume() {
        super.onResume()
        super.onDestroy()
        Log.d(tag, "onDestroy: Activity is being destroyed")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RecipeListListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement RecipeListListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeIndex = arguments?.getInt("recipe_index")
        val recipe = getRecipeList(requireContext())[recipeIndex!!]

        view.findViewById<TextView>(R.id.detailsDishNameTV).text = recipe.name
        view.findViewById<TextView>(R.id.detailsIngredientsTV).text = recipe.ingredients
        view.findViewById<TextView>(R.id.detailsInstructionsTV).text = recipe.instructions
        view.findViewById<RatingBar>(R.id.detailsRatingRB).rating = recipe.rating
        view.findViewById<TextView>(R.id.detailsDishTypeTV).text = recipe.type
        view.findViewById<TextView>(R.id.detailsPrepTimeTV).text = recipe.prepTime.toString()

        view.findViewById<ImageButton>(R.id.detailsEditDishFAB).setOnClickListener {
            listener.switchToEditRecipeDetailsFragment(
                recipe,
                requireArguments().getInt("recipe_index")
            )
        }
    }


    companion object {
        fun newInstance(data: Recipe, index: Int): Fragment {
            val fragment = RecipeDetailsFragment()
            val args = Bundle()
            val gson = Gson()
            val jsonStringData = gson.toJson(data)
            args.putString("data_key", jsonStringData)
            args.putInt("recipe_index", index)
            fragment.arguments = args
            return fragment
        }
    }
}