package com.banregio.devuapp.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Auxiliar en la creación de Fragments, por default infla el layout dado en el constructor.
 *
 * @param fragmentLayout [Int] id del recurso layout que será inflado.
 * */
abstract class DevUFragment(@LayoutRes private val fragmentLayout: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragmentLayout, container, false)
    }

}