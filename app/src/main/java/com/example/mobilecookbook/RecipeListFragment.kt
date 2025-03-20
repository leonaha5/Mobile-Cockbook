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
                context = view.context,
                openRecipeDetails = openRecipeDetails,
                recipeList = getRecipeList(view.context)
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