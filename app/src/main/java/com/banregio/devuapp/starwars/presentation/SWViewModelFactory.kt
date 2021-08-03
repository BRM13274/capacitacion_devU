package com.banregio.devuapp.starwars.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.banregio.devuapp.starwars.domain.usescases.GetFilmsUseCase
import com.banregio.devuapp.starwars.domain.usescases.StarShipsUseCase

class SWViewModelFactory(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val starShipsUseCase: StarShipsUseCase,
    private val context: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StarWarsViewModel::class.java)) {
            return StarWarsViewModel(
                getFilmsUseCase,
                starShipsUseCase,
                context
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}