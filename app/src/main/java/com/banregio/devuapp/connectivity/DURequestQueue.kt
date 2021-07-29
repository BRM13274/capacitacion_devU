package com.banregio.devuapp.connectivity

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class DURequestQueue constructor(application: Application) {

    companion object {
        @Volatile
        private var INSTANCE: DURequestQueue? = null
        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DURequestQueue(application).also {
                    INSTANCE = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(application.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

}