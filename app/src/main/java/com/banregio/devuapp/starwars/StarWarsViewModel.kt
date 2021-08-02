package com.banregio.devuapp.starwars

import android.app.Application
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.banregio.devuapp.MainActivity
import com.banregio.devuapp.R
import com.banregio.devuapp.connectivity.DURequestQueue
import com.banregio.devuapp.connectivity.StarWarsApi
import com.banregio.devuapp.util.DEFAULT_CHANNEL_ID
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
                processFilmsResponse(it)
            },
            {
                mutableUiState.postValue(SWUIState.Error(it.toString()))
            }
        )

        DURequestQueue.getInstance(appContext).addToRequestQueue(request)
    }

    fun getStarShips() {
        mutableUiState.postValue(SWUIState.Loading)
        val request = JsonObjectRequest(
            Request.Method.GET,
            StarWarsApi.STAR_SHIPS,
            null,
            {
                processStarShipsResponse(it)
            },
            {
                mutableUiState.postValue(SWUIState.Error(it.toString()))
            }
        )

        DURequestQueue.getInstance(appContext).addToRequestQueue(request)
    }

    private fun processFilmsResponse(response: JSONObject) {
        try {
            val result = response.getJSONArray("results")
            val typeOf = object : TypeToken<List<SWFilm>>() {}.type
            val filmsList: List<SWFilm> = Gson().fromJson(result.toString(), typeOf)
            mutableUiState.postValue(SWUIState.OnFilmsLoaded(filmsList))

        } catch (e: JSONException) {
            Log.d(TAG_DEBUG, e.toString())
        }
    }

    private fun processStarShipsResponse(response: JSONObject) {

    }

    fun getNotification(): Notification {
        val intent = Intent(appContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(appContext, 0, intent, 0)

        return NotificationCompat.Builder(appContext, DEFAULT_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_death_star)
            .setContentTitle(appContext.getString(R.string.lbl_notification_title))
            .setContentText(appContext.getString(R.string.lbl_notification_text))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(appContext.getString(R.string.lbl_notification_long_text))
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
    }
}