package com.hit.aliasgameapp.ui.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hit.aliasgameapp.R

class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back button
        view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btnBack)
            ?.setOnClickListener {
                findNavController().navigateUp()
            }
    }
}
