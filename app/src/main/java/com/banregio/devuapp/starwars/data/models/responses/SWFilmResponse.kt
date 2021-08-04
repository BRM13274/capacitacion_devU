package com.banregio.devuapp.starwars.data.models.responses

import com.google.gson.annotations.SerializedName

data class SWFilmResponse(
    val title: String,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("director")
    val directorName: String,
    @SerializedName("producer")
    val producerName: String,
    @SerializedName("release_date")
    val releaseDate: String
)

