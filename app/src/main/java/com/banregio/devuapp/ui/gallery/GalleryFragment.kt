package com.banregio.devuapp.ui.gallery

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.banregio.devuapp.R
import com.banregio.devuapp.util.TAG_DEBUG

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG_DEBUG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_DEBUG, "onCreate")
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG_DEBUG, "onCreateView")
        galleryViewModel =
                ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG_DEBUG, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_DEBUG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_DEBUG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_DEBUG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_DEBUG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG_DEBUG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_DEBUG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG_DEBUG, "onDetach")
    }
}