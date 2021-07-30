package com.banregio.devuapp.starwars

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.banregio.devuapp.R
import com.banregio.devuapp.connectivity.DURequestQueue
import com.banregio.devuapp.connectivity.StarWarsApi
import com.banregio.devuapp.databinding.FragmentFilmsBinding
import com.banregio.devuapp.util.DevUFragment
import com.banregio.devuapp.util.TAG_DEBUG
import com.banregio.devuapp.util.extensions.viewLifecycle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

class FilmsFragment : DevUFragment(R.layout.fragment_films) {

    private val binding by viewLifecycle(FragmentFilmsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()

    }

    private fun setListeners() {
        binding.btnFetchFilms.setOnClickListener {
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

        showLoading(true)
        DURequestQueue.getInstance(requireActivity().application).addToRequestQueue(request)
    }

    private fun requestSuccess(response: JSONObject) {
        showLoading()
        try {
            val result = response.getJSONArray("results")
            val typeOf = object : TypeToken<List<SWFilm>>() {}.type
            val filmsList: List<SWFilm> = Gson().fromJson(result.toString(), typeOf)
            showFilms(filmsList)

        } catch (e: JSONException) {
            Log.d(TAG_DEBUG, e.toString())
        }
    }

    private fun showFilms(filmsList: List<SWFilm>) {
        binding.rvFilms.run {
            layoutManager = LinearLayoutManager(context)
            adapter = FilmsAdapter().also {
                it.setItems(filmsList)
            }
        }
    }

    private fun showLoading(shouldShow: Boolean = false) {
        if (shouldShow) {
            (requireActivity() as? StarWarsActivity)?.showLoading()
        } else {
            (requireActivity() as? StarWarsActivity)?.dismissLoading()
        }
    }

}