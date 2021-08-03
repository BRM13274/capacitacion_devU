package com.banregio.devuapp.starwars.presentation

import android.app.Application
import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.banregio.devuapp.MainActivity
import com.banregio.devuapp.R
import com.banregio.devuapp.starwars.domain.usescases.GetFilmsResult
import com.banregio.devuapp.starwars.domain.usescases.GetFilmsUseCase
import com.banregio.devuapp.starwars.domain.usescases.StarShipsResult
import com.banregio.devuapp.starwars.domain.usescases.StarShipsUseCase
import com.banregio.devuapp.util.DEFAULT_CHANNEL_ID
import kotlinx.coroutines.launch

class StarWarsViewModel(
    private val filmsUseCase: GetFilmsUseCase,
    private val starShipsUseCase: StarShipsUseCase,
    private val appContext: Application
) : AndroidViewModel(appContext) {

    private val mutableUiState = MutableLiveData<SWUIState>()
    val uiState: LiveData<SWUIState>
        get() = mutableUiState

    fun getFilms() {
        mutableUiState.postValue(SWUIState.Loading)
        viewModelScope.launch {
            when (val filmsResult = filmsUseCase.execute()) {
                is GetFilmsResult.Success -> {
                    mutableUiState.postValue(SWUIState.OnFilmsLoaded(filmsResult.result))
                }
                is GetFilmsResult.Fail -> {
                    mutableUiState.postValue(SWUIState.Error(filmsResult.errorMessage))
                }
            }
        }
    }

    fun getStarShips() {
        mutableUiState.postValue(SWUIState.Loading)
        viewModelScope.launch {
            when (val starShipsResult = starShipsUseCase.execute()) {
                is StarShipsResult.Success -> {
                    mutableUiState.postValue(SWUIState.OnStarShipsLoaded(starShipsResult.result))
                }
                is StarShipsResult.Fail -> {
                    mutableUiState.postValue(SWUIState.Error(starShipsResult.errorMessage))
                }
            }
        }
    }

    fun getNotification(): Notification {
        val intent = Intent(appContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(appContext, 0, intent, 0)

        return NotificationCompat.Builder(appContext, DEFAULT_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_death_star)
            .setContentTitle(appContext.getString(R.string.lbl_notification_title))
            .setContentText(appContext.getString(R.string.lbl_notification_text))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(appContext.getString(R.string.lbl_notification_long_text))
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
    }
}