package com.example.mobilecookbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RecipeDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = Gson().fromJson(arguments?.getString("data_key"), Recipe::class.java)

        view.findViewById<TextView>(R.id.detailsDishNameTV).text = recipe.name
        view.findViewById<TextView>(R.id.detailsIngredientsTV).text = recipe.ingredients
        view.findViewById<TextView>(R.id.detailsInstructionsTV).text = recipe.instructions
        view.findViewById<RatingBar>(R.id.detailsRatingRB).rating = recipe.rating
        view.findViewById<TextView>(R.id.detailsDishTypeTV).text = recipe.type
        view.findViewById<TextView>(R.id.detailsPrepTimeTV).text = recipe.prepTime.toString()
    }

    companion object {
        fun newInstance(data: Recipe): Fragment {
            val fragment = RecipeDetailsFragment()
            val args = Bundle()
            val gson = Gson()
            val jsonStringData = gson.toJson(data)
            args.putString("data_key", jsonStringData)
            fragment.arguments = args
            return fragment
        }
    }
}