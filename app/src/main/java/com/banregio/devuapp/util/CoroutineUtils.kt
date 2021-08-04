package com.banregio.devuapp.util

import com.android.volley.toolbox.JsonObjectRequest
import com.banregio.devuapp.connectivity.DURequestQueue
import com.banregio.devuapp.starwars.data.models.request.BaseVolleyRequest
import com.banregio.devuapp.starwars.data.models.responses.ServiceResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

suspend inline fun convertToSuspend(
    request: BaseVolleyRequest,
    requestInstance: DURequestQueue
): ServiceResponse {
    return suspendCancellableCoroutine { continuation ->

        continuation.invokeOnCancellation {
            it?.printStackTrace()
        }
        val genericRequest = JsonObjectRequest(
            request.method,
            request.url,
            request.jsonObject,
            { jsonResponse ->
                continuation.resume(ServiceResponse().apply {
                    try {
                        resultSet = jsonResponse
                    } catch (e: Exception) {
                        resultSet = null
                        serviceMessage = e.printStackTrace().toString()
                    }
                })
            },
            { volleyError ->
                continuation.resume(
                    ServiceResponse().apply {
                        serviceMessage = volleyError.message ?: DEFAULT_SERVICE_ERROR
                    }
                )
            }
        )

        requestInstance.addToRequestQueue(genericRequest)
    }
}