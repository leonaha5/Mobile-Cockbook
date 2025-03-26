package com.example.mobilecookbook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import com.google.gson.Gson


class EditRecipeDetailsFragment : Fragment() {
    private lateinit var listener: RecipeListListener

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
        return inflater.inflate(R.layout.fragment_edit_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipe = Gson().fromJson(arguments?.getString("data_key"), Recipe::class.java)

        val editDetailsDishNameET: EditText? = view.findViewById(R.id.editDetailsDishNameET)
        val editDetailsIngredientsET: EditText? = view.findViewById(R.id.editDetailsIngredientsET)
        val editDetailsInstructionsET: EditText? = view.findViewById(R.id.editDetailsInstructionsET)
        val editDetailsRatingRB: RatingBar? = view.findViewById(R.id.editDetailsRatingRB)
        val editDetailsPrepTimeS: Slider? = view.findViewById(R.id.editDetailsPrepTimeS)

        editDetailsDishNameET?.setText(recipe.name)
        editDetailsIngredientsET?.setText(recipe.ingredients)
        editDetailsInstructionsET?.setText(recipe.instructions)
        editDetailsRatingRB?.rating = recipe.rating
        editDetailsPrepTimeS?.value = recipe.prepTime.toFloat()


        view.findViewById<FloatingActionButton>(R.id.saveEditedRecipeFAB)?.setOnClickListener {
            val newRecipe = Recipe(
                editDetailsDishNameET!!.text.toString(),
                "",
                editDetailsPrepTimeS!!.value.toInt(),
                editDetailsRatingRB!!.rating,
                editDetailsIngredientsET!!.text.toString(),
                editDetailsInstructionsET!!.text.toString(),
            )
            val recipeIndex = requireArguments().getInt("recipe_index")
            editRecipe(view.context, newRecipe, recipeIndex)
            parentFragmentManager.popBackStack()
        }

    }

    companion object {
        fun newInstance(data: Recipe, index: Int): Fragment {
            val fragment = EditRecipeDetailsFragment()
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