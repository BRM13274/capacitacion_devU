package com.banregio.devuapp.starwars.presentation.starships

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.banregio.devuapp.R
import com.banregio.devuapp.databinding.FragmentStarShipsBinding
import com.banregio.devuapp.starwars.di.StarWarsModule
import com.banregio.devuapp.starwars.domain.models.SWStarShip
import com.banregio.devuapp.starwars.presentation.SWUIState
import com.banregio.devuapp.starwars.presentation.StarWarsActivity
import com.banregio.devuapp.starwars.presentation.StarWarsViewModel
import com.banregio.devuapp.util.DevUFragment
import com.banregio.devuapp.util.extensions.viewLifecycle


class StarShipsFragment : DevUFragment(R.layout.fragment_star_ships) {

    private val binding by viewLifecycle(FragmentStarShipsBinding::bind)
    private val viewModel: StarWarsViewModel by activityViewModels {
        StarWarsModule.provideViewModelFactory(requireActivity().application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observe(viewLifecycleOwner, { handleUiState(it) })
        setListeners()
    }

    private fun setListeners() {
        binding.btnFetchStarHips.run {
            setOnClickListener {
                viewModel.getStarShips()
                text = getString(R.string.btn_reload_star_ships)
            }
        }
    }

    private fun handleUiState(uiState: SWUIState) {
        when (uiState) {
            is SWUIState.Loading -> {
                (requireActivity() as? StarWarsActivity)?.showLoading()
            }
            is SWUIState.OnStarShipsLoaded -> {
                (requireActivity() as? StarWarsActivity)?.dismissLoading()
                showStarShips(uiState.starShips)
            }
            is SWUIState.Error -> {
                (requireActivity() as? StarWarsActivity)?.dismissLoading()
            }
        }
    }

    private fun showStarShips(starShips: List<SWStarShip>) {
        binding.rvStarShips.run {
            val customLayoutManager = LinearLayoutManager(context)
            layoutManager = customLayoutManager
            adapter = StarShipsAdapter().also {
                it.setItems(starShips)
            }
            addItemDecoration(DividerItemDecoration(context, customLayoutManager.orientation))
        }
    }

}