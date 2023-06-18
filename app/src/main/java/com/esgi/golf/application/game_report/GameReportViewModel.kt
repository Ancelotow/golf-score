package com.esgi.golf.application.game_report

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esgi.golf.domain.services.GameReportService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameReportViewModel @Inject constructor(private val service: GameReportService) :
    ViewModel() {

    private val _gameState = MutableLiveData<GameReportState>()
    val gameState = _gameState

    fun getGame(id: Int) {
        viewModelScope.launch {
            _gameState.value = GameReportState.loading()
            try {
                val game = service.getGame(id)
                _gameState.value = GameReportState.success(game)
            } catch (e: Exception) {
                _gameState.value = GameReportState.error(e)
            }
        }
    }
}