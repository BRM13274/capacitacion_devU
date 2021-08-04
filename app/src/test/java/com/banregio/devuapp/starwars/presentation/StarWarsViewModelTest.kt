package com.banregio.devuapp.starwars.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.Observer
import com.banregio.devuapp.starwars.domain.models.SWFilm
import com.banregio.devuapp.starwars.domain.usescases.GetFilmsResult
import com.banregio.devuapp.starwars.domain.usescases.GetFilmsUseCase
import com.banregio.devuapp.starwars.domain.usescases.StarShipsUseCase
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class StarWarsViewModelTest {

    private lateinit var viewModel: StarWarsViewModel
    private lateinit var observer: Observer<SWUIState>
    private val uiState = slot<SWUIState>()
    private val filmsUseCase: GetFilmsUseCase = mockk()
    private val starShipsUseCase: StarShipsUseCase = mockk()

    @Before
    fun setup() {
        val testCoroutineDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testCoroutineDispatcher)
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }
        })

        viewModel = StarWarsViewModel(
            filmsUseCase,
            starShipsUseCase,
            mockk()
        )

        observer = spyk(Observer {  })
        viewModel.uiState.observeForever(observer)
        justRun {
            observer.onChanged(capture(uiState))
        }
    }

    @Test
    fun `Test fetch films`() {
        val swFilms: MutableList<SWFilm> = mutableListOf()
        swFilms.add(SWFilm("", 1, "", "", ""))
        coEvery { filmsUseCase.execute() } returns GetFilmsResult.Success(swFilms)
        viewModel.getFilms()
        uiState.captured shouldBeInstanceOf SWUIState.OnFilmsLoaded::class
    }

    @After
    fun clean() {
        viewModel.uiState.removeObserver(observer)
    }

}