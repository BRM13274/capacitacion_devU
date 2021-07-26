package com.banregio.devuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val activityButton = findViewById<Button>(R.id.secondary_button)

        //método para captar el evento clic
        activityButton.setOnClickListener {
            buttonAction()
        }


    }

    private fun makeToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun buttonAction() {
        val stringFromResource = getString(R.string.msg_congratulations)
        makeToastMessage(stringFromResource)
    }


    /*
    *
    * ¿Quieres hacer crashear tu aplicación? Intenta correr este código ;)
    *
    * */
    private fun crashApp() {
        val notNullVariable: Int? = null
        val customMessage = "El valor en doble es: ${notNullVariable!!.toDouble()}"
        makeToastMessage(customMessage)
    }

}