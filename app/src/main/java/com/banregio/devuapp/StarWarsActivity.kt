package com.banregio.devuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.banregio.devuapp.connectivity.DURequestQueue
import com.banregio.devuapp.connectivity.StarWarsApi
import com.banregio.devuapp.util.TAG_DEBUG
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class StarWarsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars)
        findViewById<Button>(R.id.fetch_films).setOnClickListener {
            getFilms()
        }
    }


    private fun getFilms() {
        val request = JsonObjectRequest(
            Request.Method.GET,
            StarWarsApi.FILMS,
            null,
            {
                requestSuccess(it)
            },
            {

            }
        )

        DURequestQueue.getInstance(application).addToRequestQueue(request)
    }


    private fun requestSuccess(response: JSONObject) {

        try {
            val result = response.getJSONArray("results")
            val typeOf = object : TypeToken<List<SWFilm>>(){}.type
            val filmsList: List<SWFilm> = Gson().fromJson(result.toString(), typeOf)
            showFilms(filmsList)

        } catch (e: JSONException) {
            Log.d(TAG_DEBUG, e.toString())
        }
    }

    private fun showFilms(filmsList: List<SWFilm>) {
        val rv: RecyclerView = findViewById(R.id.rv_films)
        rv.run {
            layoutManager = LinearLayoutManager(context)
            adapter = FilmsAdapter().also {
                it.setItems(filmsList)
            }
        }
    }

}