package com.banregio.devuapp.starwars.domain.usescases

import com.banregio.devuapp.starwars.domain.models.SWFilm
import com.banregio.devuapp.starwars.domain.repositories.StarWarsRepository
import com.banregio.devuapp.util.DevUResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetFilmsUseCaseTest {

    private val mockedRepository = mockk<StarWarsRepository>()
    private lateinit var useCase: GetFilmsUseCase

    @Before
    fun setup() {
        useCase = GetFilmsUseCase(mockedRepository)
    }

    @Test
    fun `Test execute`() {
        val simpleList: MutableList<SWFilm> = mutableListOf()
        simpleList.add(SWFilm("", 1, "", "", ""))
        val mockSuccess = mockk<DevUResponse.Successful<List<SWFilm>>>()
        every { mockSuccess.content } returns simpleList
        coEvery { mockedRepository.getFilmsList() } returns mockSuccess
        runBlocking {
            val response = useCase.execute()
            response shouldBeInstanceOf GetFilmsResult.Success::class
            when(response) {
                is GetFilmsResult.Success -> {
                    response.result[0] shouldBeInstanceOf SWFilm::class
                    response.result[0].episodeId shouldBeEqualTo  1
                }
                else -> {
                    Assert.fail("El resultado no fue Success")
                }
            }
        }
    }
}