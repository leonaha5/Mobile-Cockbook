package com.example.mobilecookbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

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
        view.findViewById<TextView>(R.id.detailsDishNameTV)?.text = arguments?.getString("data_key")
    }

    companion object {
        fun newInstance(data: String): Fragment {
            val fragment = RecipeDetailsFragment()
            val args = Bundle()
            args.putString("data_key", data)
            fragment.arguments = args
            return fragment
        }
    }
}