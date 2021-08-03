package com.banregio.devuapp.starwars.di

import android.app.Application
import com.banregio.devuapp.connectivity.DURequestQueue
import com.banregio.devuapp.starwars.data.repositories.StarWarsRepositoryImp
import com.banregio.devuapp.starwars.domain.repositories.StarWarsRepository
import com.banregio.devuapp.starwars.domain.usescases.GetFilmsUseCase
import com.banregio.devuapp.starwars.presentation.SWViewModelFactory

object StarWarsModule {

    fun provideViewModelFactory(app: Application): SWViewModelFactory {
        val repository = provideSWRepository(providesRequestQueue(app))
        return SWViewModelFactory(
            providesFilmsUseCase(repository),
            app
        )
    }

    private fun providesFilmsUseCase(repository: StarWarsRepository) = GetFilmsUseCase(repository)

    private fun provideSWRepository(queue: DURequestQueue): StarWarsRepository =
        StarWarsRepositoryImp.getInstance(queue)

    private fun providesRequestQueue(app: Application): DURequestQueue =
        DURequestQueue.getInstance(app)

}