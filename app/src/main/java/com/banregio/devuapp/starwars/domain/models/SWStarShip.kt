package com.banregio.devuapp.starwars.domain.models

data class SWStarShip(
    val name: String,
    val model: String?,
    val costInCredits: String?,
    val maxAtmosphereSpeed: String?,
    val crewLimits: String?,
    val starShipClass: String?,
    val appearanceFilmsList: List<String>?
)