package com.example.mobilecookbook

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        initializeRecyclerView()

    }

    private fun initializeRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recipesRecyclerView)

        val adapter =
            RecipeAdapter(
                recipeList = mutableListOf(
                    Recipe("Spaghetti Bolognese", "Pasta", 30),
                    Recipe("Chicken Fajitas", "Mexican", 20),
                    Recipe("Vegetable Stir Fry", "Asian", 15),
                    Recipe("Beef Burgers", "American", 25),
                    Recipe("Grilled Salmon", "Seafood", 18)
                ),
            )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}