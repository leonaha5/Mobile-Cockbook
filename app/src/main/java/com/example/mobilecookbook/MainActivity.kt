package com.example.mobilecookbook

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity(), RecipeListListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //TODO rating bar doesnt update after editing recipe
        //TODO add ability to add images to recipes
        //TODO add ability to edit recipes images
        //TODO animation on delete is ugly as fuck
        //TODO the ui is ugly as fuck
        //TODO navigation animations

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

    override fun switchToEditRecipeDetailsFragment(data: Recipe, index: Int) {
        replaceFragment(EditRecipeDetailsFragment.newInstance(data, index))
    }

    override fun switchToRecipeDetailsFragment(data: Recipe, index: Int) {
        replaceFragment(RecipeDetailsFragment.newInstance(data, index))
    }

    override fun switchToRecipeListFragment() {
        replaceFragment(RecipeListFragment.newInstance())
    }

}