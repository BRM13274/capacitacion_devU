package com.banregio.devuapp.starwars.presentation

import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.activityViewModels
import com.banregio.devuapp.R
import com.banregio.devuapp.databinding.FragmentNotificationBinding
import com.banregio.devuapp.starwars.di.StarWarsModule
import com.banregio.devuapp.util.DevUFragment
import com.banregio.devuapp.util.extensions.viewLifecycle

class NotificationFragment : DevUFragment(R.layout.fragment_notification) {

    private val binding by viewLifecycle(FragmentNotificationBinding::bind)
    private val viewModel: StarWarsViewModel by activityViewModels {
        StarWarsModule.provideViewModelFactory(requireActivity().application)
    }

    companion object {
        const val NOTIFICATION_ID = 1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnShowNotification.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {
        with(NotificationManagerCompat.from(requireContext())) {
            notify(
                NOTIFICATION_ID,
                viewModel.getNotification()
            )
        }
    }
}