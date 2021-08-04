package com.banregio.devuapp.starwars.data.models.request

import org.json.JSONObject

data class BaseVolleyRequest(
    val method: Int,
    val url: String,
    val jsonObject: JSONObject?
)