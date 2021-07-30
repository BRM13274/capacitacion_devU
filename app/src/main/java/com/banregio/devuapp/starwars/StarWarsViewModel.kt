package com.banregio.devuapp.starwars

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.banregio.devuapp.connectivity.DURequestQueue
import com.banregio.devuapp.connectivity.StarWarsApi
import com.banregio.devuapp.util.TAG_DEBUG
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class StarWarsViewModel(private val appContext: Application) : AndroidViewModel(appContext) {

    private val mutableUiState = MutableLiveData<SWUIState>()
    val uiState: LiveData<SWUIState>
        get() = mutableUiState

    fun getFilms() {
        mutableUiState.postValue(SWUIState.Loading)
        val request = JsonObjectRequest(
            Request.Method.GET,
            StarWarsApi.FILMS,
            null,
            {
                requestSuccess(it)
            },
            {
                mutableUiState.postValue(SWUIState.Error(it.toString()))
            }
        )

        DURequestQueue.getInstance(appContext).addToRequestQueue(request)
    }

    private fun requestSuccess(response: JSONObject) {
        try {
            val result = response.getJSONArray("results")
            val typeOf = object : TypeToken<List<SWFilm>>() {}.type
            val filmsList: List<SWFilm> = Gson().fromJson(result.toString(), typeOf)
            mutableUiState.postValue(SWUIState.OnFilmsLoaded(filmsList))

        } catch (e: JSONException) {
            Log.d(TAG_DEBUG, e.toString())
        }
    }
}