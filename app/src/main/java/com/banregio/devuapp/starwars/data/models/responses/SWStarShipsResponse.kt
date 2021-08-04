package com.banregio.devuapp.starwars.data.models.responses

import com.google.gson.annotations.SerializedName

/*
*
* {
			"name": "CR90 corvette",
			"model": "CR90 corvette",
			"manufacturer": "Corellian Engineering Corporation",
			"cost_in_credits": "3500000",
			"length": "150",
			"max_atmosphering_speed": "950",
			"crew": "30-165",
			"passengers": "600",
			"cargo_capacity": "3000000",
			"consumables": "1 year",
			"hyperdrive_rating": "2.0",
			"MGLT": "60",
			"starship_class": "corvette",
			"pilots": [],
			"films": [
				"https://swapi.dev/api/films/1/",
				"https://swapi.dev/api/films/3/",
				"https://swapi.dev/api/films/6/"
			],
			"created": "2014-12-10T14:20:33.369000Z",
			"edited": "2014-12-20T21:23:49.867000Z",
			"url": "https://swapi.dev/api/starships/2/"
		}
*
*
* */

class SWStarShipsResponse(
    val name: String?,
    val model: String?,
    val manufacturer: String?,
    @SerializedName("cost_in_credits")
    val costInCredits: String?,
    val length: String?,
    @SerializedName("max_atmosphering_speed")
    val maxAtmosphereSpeed: String?,
    @SerializedName("crew")
    val crewLimits: String?,
    @SerializedName("passengers")
    val passengersLimit: String?,
    @SerializedName("cargo_capacity")
    val cargoCapacity: String?,
    val consumables: String?,
    @SerializedName("hyperdrive_rating")
    val hyperDriveRating: String?,
    @SerializedName("MGLT")
    val mglt: String?,
    @SerializedName("starship_class")
    val starShipClass: String?,
    @SerializedName("film")
    val appearanceFilmsList: List<String>?,
    @SerializedName("created")
    val createdDate: String?,
    @SerializedName("edited")
    val editedDate: String?
)