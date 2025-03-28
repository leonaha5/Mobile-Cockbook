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

        if (savedInstanceState == null) {
            replaceFragment(RecipeListFragment(), backStack = false)
        }
    }

    private fun replaceFragment(fragment: Fragment, backStack: Boolean = true) {
        supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in_right, R.anim.slide_out_left,
            R.anim.slide_in_left, R.anim.slide_out_right
        )
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