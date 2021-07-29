package com.banregio.devuapp

import com.google.gson.annotations.SerializedName

data class SWFilm(
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

