package com.esgi.golf.application.score

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esgi.golf.domain.models.Round
import com.esgi.golf.domain.services.ScoreCalculatorService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val calculatorService: ScoreCalculatorService,
) : ViewModel() {

    private val _gameState = MutableLiveData<ScoreState>()
    val gameState = _gameState

    init {
        getGame()
    }

    fun addShot(round: Round) {
        calculatorService.addShot(round.hole, round.player)
    }

    fun removeShot(round: Round) {
        calculatorService.removeShot(round.hole, round.player)
    }

    fun getGame() {
        viewModelScope.launch {
            _gameState.value = ScoreState.loading()
            try {
                val game = calculatorService.getGame()
                _gameState.value = ScoreState.success(game)
            } catch (e: Exception) {
                _gameState.value = ScoreState.error(e)
            }
        }
    }

}