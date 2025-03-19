package com.example.mobilecookbook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecipeListFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recipesRecyclerView)
        val openRecipeDetails: (Recipe) -> Unit = { data ->
            listener.switchToRecipeDetailsFragment(data)
        }

        val buttonSwitchFragment = view.findViewById<FloatingActionButton>(R.id.addRecipeFAB)
        buttonSwitchFragment.setOnClickListener {
            listener.switchToAddRecipeFragment()
        }

        val adapter =
            RecipeAdapter(
                openRecipeDetails = openRecipeDetails,
                recipeList = mutableListOf(
                    Recipe(
                        "Spaghetti Bolognese",
                        "Pasta",
                        30,
                        4f,
                        "Spaghetti, Minced Beef, Tomato Sauce, Onion, Garlic",
                        "Cook spaghetti, Prepare bolognese sauce, Combine spaghetti and sauce"
                    ),
                    Recipe(
                        "Chicken Fajitas",
                        "Mexican",
                        20,
                        5f,
                        "Chicken Breast, Bell Peppers, Onion, Tortilla, Salsa",
                        "Slice chicken and vegetables, Cook chicken and vegetables, Serve with tortilla and salsa"
                    ),
                    Recipe(
                        "Vegetable Stir Fry",
                        "Asian",
                        15,
                        4f,
                        "Broccoli, Carrot, Bell Peppers, Onion, Soy Sauce",
                        "Chop vegetables, Heat wok or pan, Stir-fry vegetables"
                    ),
                    Recipe(
                        "Beef Burgers",
                        "American",
                        25,
                        5f,
                        "Minced Beef, Bread, Lettuce, Tomato, Cheese",
                        "Prepare burger patties, Grill or cook burgers, Assemble burgers"
                    ),
                    Recipe(
                        "Grilled Salmon",
                        "Seafood",
                        18,
                        5f,
                        "Salmon Fillet, Lemon, Butter, Salt, Pepper",
                        "Season salmon, Grill salmon, Serve with lemon and butter"
                    )
                )
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