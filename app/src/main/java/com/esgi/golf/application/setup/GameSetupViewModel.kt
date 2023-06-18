package com.esgi.golf.application.setup

import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.services.GameSetupServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameSetupViewModel @Inject constructor(
    private val gameSetupService: GameSetupServiceInterface
) : ViewModel() {

    private val _gameSetupState = MutableLiveData<GameSetupState>()
    val gameSetupState = _gameSetupState

    fun addGame(game: Game){
        viewModelScope.launch {
            _gameSetupState.value = GameSetupState.loading()
            try {
                val gameId = gameSetupService.addGame(game)
                _gameSetupState.value = GameSetupState.success(gameId)
            } catch (e: Exception) {
                _gameSetupState.value = GameSetupState.error(e)
            }
        }
    }
}