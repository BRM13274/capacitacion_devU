package com.banregio.devuapp.starwars

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.banregio.devuapp.R
import com.banregio.devuapp.databinding.FragmentStarWarsBinding
import com.banregio.devuapp.util.DevUFragment
import com.banregio.devuapp.util.extensions.viewLifecycle

class StarWarsFragment : DevUFragment(R.layout.fragment_star_wars) {

    private val binding by viewLifecycle(FragmentStarWarsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.btnGoToFilms.setOnClickListener {
            findNavController().navigate(R.id.action_starWarsFragment_to_filmsFragment)
        }

        binding.btnGoToStarShips.setOnClickListener {
            findNavController().navigate(R.id.action_starWarsFragment_to_starShipsFragment)
        }
    }

}