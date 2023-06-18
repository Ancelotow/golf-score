package com.esgi.golf.application.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esgi.golf.domain.services.GameListServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val gameListService: GameListServiceInterface
) : ViewModel(){

    private val _gameListState = MutableLiveData<GameListState>()
    val gameListState = _gameListState

    fun getGames(){
        _gameListState.value = GameListState.loading()
        try {
            val games = gameListService.getGames()
            _gameListState.value = GameListState.success(games)
        } catch (e: Exception) {
            _gameListState.value = GameListState.error(e)
        }
    }
}