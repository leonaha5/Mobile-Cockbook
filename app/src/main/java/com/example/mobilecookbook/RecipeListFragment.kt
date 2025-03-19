package com.example.mobilecookbook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RecipeListFragment : Fragment() {
    private lateinit var listener: RecipeListListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // sprawdzamy czy Aktywność (context) implementuje interface (FragmentAListener
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
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recipesRecyclerView)
        val openRecipeDetails: (String) -> Unit = { data ->
            listener.switchToRecipeDetailsFragment(data)
        }

        val adapter =
            RecipeAdapter(
                openRecipeDetails = openRecipeDetails,
                recipeList = mutableListOf(
                    Recipe("Spaghetti Bolognese", "Pasta", 30),
                    Recipe("Chicken Fajitas", "Mexican", 20),
                    Recipe("Vegetable Stir Fry", "Asian", 15),
                    Recipe("Beef Burgers", "American", 25),
                    Recipe("Grilled Salmon", "Seafood", 18)
                ),
            )

        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            RecipeListFragment().apply {
                arguments = Bundle()
            }
    }
}