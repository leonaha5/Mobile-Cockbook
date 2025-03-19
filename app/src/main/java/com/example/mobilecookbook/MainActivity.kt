package com.example.mobilecookbook

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


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

        if (savedInstanceState == null) {
            replaceFragment(RecipeListFragment(), backStack = false)
        }

        val buttonSwitchFragment = findViewById<FloatingActionButton>(R.id.addRecipeFAB)
        buttonSwitchFragment.setOnClickListener {
            replaceFragment(AddRecipeFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment, backStack: Boolean = true) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .also { if (backStack) it.addToBackStack(null) }
            .commit()
    }

    override fun switchToRecipeDetailsFragment(data: String) {
        replaceFragment(RecipeDetailsFragment.newInstance(data))

    }

}