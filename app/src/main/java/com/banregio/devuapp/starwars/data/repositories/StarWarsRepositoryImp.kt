package com.banregio.devuapp.starwars.data.repositories

import android.util.Log
import com.android.volley.Request
import com.banregio.devuapp.connectivity.DURequestQueue
import com.banregio.devuapp.connectivity.StarWarsApi
import com.banregio.devuapp.starwars.data.models.request.BaseVolleyRequest
import com.banregio.devuapp.starwars.data.models.responses.SWFilmResponse
import com.banregio.devuapp.starwars.domain.models.SWFilm
import com.banregio.devuapp.starwars.domain.repositories.StarWarsRepository
import com.banregio.devuapp.util.DevUResponse
import com.banregio.devuapp.util.TAG_DEBUG
import com.banregio.devuapp.util.convertToSuspend
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class StarWarsRepositoryImp private constructor(private val requestInstance: DURequestQueue) :
    StarWarsRepository {

    companion object {
        private var INSTANCE: StarWarsRepositoryImp? = null
        fun getInstance(requestInstance: DURequestQueue): StarWarsRepositoryImp {
            return INSTANCE ?: run {
                INSTANCE = StarWarsRepositoryImp(requestInstance)
                INSTANCE!!
            }
        }
    }

    override suspend fun getFilmsList(): DevUResponse<List<SWFilm>> {
        val request = BaseVolleyRequest(
            Request.Method.GET,
            StarWarsApi.FILMS,
            null
        )
        val result = convertToSuspend(request, requestInstance)
        return result.resultSet?.let {
            DevUResponse.Successful(processFilmsResponse(it))
        } ?: run {
            DevUResponse.Failed(result.serviceMessage)
        }
    }

    private fun processFilmsResponse(response: JSONObject): List<SWFilm> {
        return try {
            val result = response.getJSONArray("results")
            val typeOf = object : TypeToken<List<SWFilmResponse>>() {}.type
            val serviceList: List<SWFilmResponse> = Gson().fromJson(result.toString(), typeOf)
            val filmsList = mutableListOf<SWFilm>()
            serviceList.forEach {
                filmsList.add(
                    SWFilm(
                        it.title,
                        it.episodeId,
                        it.directorName,
                        it.producerName,
                        it.releaseDate
                    )
                )
            }
            return filmsList

        } catch (e: JSONException) {
            Log.d(TAG_DEBUG, e.toString())
            listOf()
        }
    }

}