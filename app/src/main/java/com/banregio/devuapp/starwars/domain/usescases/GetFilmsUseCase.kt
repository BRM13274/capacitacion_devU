package com.banregio.devuapp.starwars.domain.usescases

import com.banregio.devuapp.starwars.domain.models.SWFilm
import com.banregio.devuapp.starwars.domain.repositories.StarWarsRepository
import com.banregio.devuapp.util.DevUResponse

class GetFilmsUseCase(private val repository: StarWarsRepository) {
    suspend fun execute(): GetFilmsResult {
        return when(val rs = repository.getFilmsList()) {
            is DevUResponse.Successful -> {
                GetFilmsResult.Success(rs.content)
            }
            is DevUResponse.Failed -> {
                GetFilmsResult.Fail(rs.errorMessage)
            }
        }
    }
}

sealed class GetFilmsResult {
    data class Success(val result: List<SWFilm>) : GetFilmsResult()
    data class Fail(val errorMessage: String) : GetFilmsResult()
}