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
            throw RuntimeException("$context must implement RecipeListListener")
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

        view.findViewById<FloatingActionButton>(R.id.addRecipeFAB).setOnClickListener {
            listener.switchToAddRecipeFragment()
        }

        val openRecipeDetails: (Recipe, Int) -> Unit = { data, index ->
            listener.switchToRecipeDetailsFragment(data, index)
        }
        
        val adapter =
            RecipeAdapter(
                context = view.context,
                openRecipeDetails = openRecipeDetails,
                recipeList = getRecipeList(view.context)
            )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recipesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance() = RecipeListFragment()
    }
}