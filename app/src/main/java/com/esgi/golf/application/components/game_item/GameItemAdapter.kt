package com.esgi.golf.application.components.game_item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Game

class GameListAdapter(private val games: List<Game>, val onItemClick: (position: Int) -> Unit) : RecyclerView.Adapter<GameListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListHolder {
        return GameListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_list, parent, false),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: GameListHolder, position: Int) {
        holder.setGame(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}
