package com.esgi.golf.application.components.game_item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Game


class GameListHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var gameName = v.findViewById<TextView>(R.id.item_game_list_game_name_text_view)
    private var gameDate = v.findViewById<TextView>(R.id.item_game_list_game_date_text_view)
    private var gameWinner = v.findViewById<TextView>(R.id.item_game_list_game_winner_text_view)
    fun setGame(game: Game){
        gameName.text = game.name
        gameDate.text = game.date.toString()
        gameWinner.text = "Winner : ${game.winner?.name ?: ""} ${game.winner?.firstname ?: ""}"
    }
}