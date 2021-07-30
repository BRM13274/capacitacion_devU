package com.banregio.devuapp.starwars

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.banregio.devuapp.R
import com.banregio.devuapp.databinding.FragmentFilmsBinding
import com.banregio.devuapp.util.DevUFragment
import com.banregio.devuapp.util.TAG_DEBUG
import com.banregio.devuapp.util.extensions.viewLifecycle

class FilmsFragment : DevUFragment(R.layout.fragment_films) {

    private val binding by viewLifecycle(FragmentFilmsBinding::bind)
    private val viewModel: StarWarsViewModel by activityViewModels()
    private val uiState = Observer<SWUIState> { uiState ->
        when(uiState) {
            is SWUIState.Loading -> {
                showLoading(true)
            }
            is SWUIState.Error -> {
                Toast.makeText(requireContext(), "Error de connection", Toast.LENGTH_SHORT).show()
                Log.d(TAG_DEBUG, uiState.errorMessage)
            }
            is SWUIState.OnFilmsLoaded -> {
                showLoading(false)
                showFilms(uiState.filmsList)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observe(viewLifecycleOwner, uiState)
        setListeners()

    }

    private fun setListeners() {
        binding.btnFetchFilms.setOnClickListener {
            viewModel.getFilms()
        }
    }

    private fun showFilms(filmsList: List<SWFilm>) {
        binding.rvFilms.run {
            layoutManager = LinearLayoutManager(context)
            adapter = FilmsAdapter().also {
                it.setItems(filmsList)
            }
        }
    }

    private fun showLoading(shouldShow: Boolean = false) {
        if (shouldShow) {
            (requireActivity() as? StarWarsActivity)?.showLoading()
        } else {
            (requireActivity() as? StarWarsActivity)?.dismissLoading()
        }
    }

}