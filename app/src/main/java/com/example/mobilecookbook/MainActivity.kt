package com.example.mobilecookbook

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity(), RecipeListListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //TODO add the ability to remove
        // TODO add an icon for the add recipe fab
        //TODO add the ability to edit racipes


        if (savedInstanceState == null) {
            replaceFragment(RecipeListFragment(), backStack = false)
        }
    }

    private fun replaceFragment(fragment: Fragment, backStack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .also { if (backStack) it.addToBackStack(null) }
            .commit()
    }

    override fun switchToAddRecipeFragment() {
        replaceFragment(AddRecipeFragment.newInstance())
    }

    override fun switchToRecipeDetailsFragment(data: Recipe) {
        replaceFragment(RecipeDetailsFragment.newInstance(data))
    }

    override fun switchToRecipeListFragment() {
        replaceFragment(RecipeListFragment.newInstance())
    }

}