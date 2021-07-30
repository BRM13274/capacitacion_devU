package com.banregio.devuapp.starwars

abstract class SWUIState {

    object Loading : SWUIState()
    data class Error(val errorMessage: String) : SWUIState()
    data class OnFilmsLoaded(val filmsList: List<SWFilm>) : SWUIState()

}