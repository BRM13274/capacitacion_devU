package com.banregio.devuapp.starwars.presentation

import com.banregio.devuapp.starwars.domain.models.SWFilm
import com.banregio.devuapp.starwars.domain.models.SWStarShip

abstract class SWUIState {

    object Loading : SWUIState()
    data class Error(val errorMessage: String) : SWUIState()
    data class OnFilmsLoaded(val filmsList: List<SWFilm>) : SWUIState()
    data class OnStarShipsLoaded(val starShips: List<SWStarShip>) : SWUIState()

}