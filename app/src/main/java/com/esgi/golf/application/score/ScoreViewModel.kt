package com.esgi.golf.application.score

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esgi.golf.application.score.game_state.GameState
import com.esgi.golf.application.score.score_state.ScoreState
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Round
import com.esgi.golf.domain.services.ScoreCalculatorService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val calculatorService: ScoreCalculatorService,
) : ViewModel() {

    private val _gameState = MutableLiveData<GameState>()
    val gameState = _gameState

    private val _scoreState = MutableLiveData<ScoreState>()
    val scoreState = _scoreState

    fun addShot(round: Round, id: Int) {
        calculatorService.addShot(round.hole, round.player, id)
    }

    fun removeShot(round: Round, id: Int) {
        calculatorService.removeShot(round.hole, round.player, id)
    }

    fun getGame(id: Int) {
        viewModelScope.launch {
            _gameState.value = GameState.loading()
            try {
                val game = calculatorService.getGame(id)
                _gameState.value = GameState.success(game)
            } catch (e: Exception) {
                _gameState.value = GameState.error(e)
            }
        }
    }

    fun getScore(hole: Hole, id: Int) {
        viewModelScope.launch {
            _scoreState.value = ScoreState.loading()
            try {
                val game = calculatorService.getGame(id)
                val rounds = game.rounds.filter { round -> round.hole.id == hole.id }
                _scoreState.value = ScoreState.success(rounds)
            } catch (e: Exception) {
                _scoreState.value = ScoreState.error(e)
            }
        }
    }

    fun finishGame(id: Int) {
        viewModelScope.launch {
            _gameState.value = GameState.loading()
            try {
                val game = calculatorService.getGame(id)
                calculatorService.finishGame(game)
                _gameState.value = GameState.success(game)
            } catch (e: Exception) {
                _gameState.value = GameState.error(e)
            }
        }
    }
}