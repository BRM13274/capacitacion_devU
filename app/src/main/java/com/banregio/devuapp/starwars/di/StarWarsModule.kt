package com.banregio.devuapp.starwars.di

import com.banregio.devuapp.connectivity.DURequestQueue
import com.banregio.devuapp.starwars.data.repositories.StarWarsRepositoryImp
import com.banregio.devuapp.starwars.domain.repositories.StarWarsRepository
import com.banregio.devuapp.starwars.domain.usescases.GetFilmsUseCase

object StarWarsModule {


    private fun providesFilmsUseCase(repository: StarWarsRepository) = GetFilmsUseCase(repository)

    private fun provideSWRepository(queue: DURequestQueue): StarWarsRepository =
        StarWarsRepositoryImp.getInstance(queue)

}