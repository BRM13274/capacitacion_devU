package com.banregio.devuapp.starwars.domain.usescases

import com.banregio.devuapp.starwars.domain.models.SWStarShip
import com.banregio.devuapp.starwars.domain.repositories.StarWarsRepository
import com.banregio.devuapp.util.DevUResponse

class StarShipsUseCase(private val repository: StarWarsRepository) {
    suspend fun execute(): StarShipsResult {
        return when(val rs = repository.getStarShipsList()) {
            is DevUResponse.Successful -> {
                StarShipsResult.Success(rs.content)
            }
            is DevUResponse.Failed -> {
                StarShipsResult.Fail(rs.errorMessage)
            }
        }
    }
}

sealed class StarShipsResult {
    data class Success(val result: List<SWStarShip>) : StarShipsResult()
    data class Fail(val errorMessage: String) : StarShipsResult()
}