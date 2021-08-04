package com.banregio.devuapp.starwars.data.models.responses

import org.json.JSONObject

/**
 * Clase auxiliar para obtener una respuesta genérica del servicio.
 * */
class ServiceResponse {
    var resultSet: JSONObject? = null
    var serviceMessage: String = ""
}