package com.example.mobilecookbook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.slider.Slider


class AddRecipeFragment : Fragment() {
    private lateinit var listener: RecipeListListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is RecipeListListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement  FragmentAListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun checkEmptyFields(vararg fields: EditText): Boolean {
            for (field in fields) {
                if (field.text.toString().trim().isEmpty()) {
                    field.error = "Please fill this field"
                    return false
                }
            }
            return true
        }

        val addRecipeButton = view.findViewById<Button>(R.id.AddRecipeButton)
        addRecipeButton.setOnClickListener {
            val addRecipeNameET = view.findViewById<EditText>(R.id.AddRecipeNameET)
            val addRecipeIngredientsET = view.findViewById<EditText>(R.id.AddRecipeIngredientsET)
            val addRecipeInstructionsET = view.findViewById<EditText>(R.id.AddRecipeInstructionsET)
            val addRecipePrepTimeS = view.findViewById<Slider>(R.id.addRecipePrepTimeS)
            val addRecipeRB = view.findViewById<RatingBar>(R.id.AddRecipeRB)

            addRecipePrepTimeS.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
                Toast.makeText(
                    requireContext(), value.toInt()
                        .toString() + " mins", Toast.LENGTH_SHORT
                ).show()
            })

            if (!checkEmptyFields(
                    addRecipeNameET,
                    addRecipeIngredientsET,
                    addRecipeInstructionsET,
                )
            ) {
                Toast.makeText(context, "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val recipe = Recipe(
                addRecipeNameET.text.toString().trim(),
                "",
                addRecipePrepTimeS.value.toInt(),
                addRecipeRB.rating,
                addRecipeIngredientsET.text.toString().trim(),
                addRecipeInstructionsET.text.toString().trim(),
            )
            addRecipe(view.context, recipe)
            parentFragmentManager.popBackStack()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = AddRecipeFragment()
    }
}