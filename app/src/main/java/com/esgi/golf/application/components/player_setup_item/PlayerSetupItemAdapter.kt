package com.esgi.golf.application.components.player_setup_item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Player

class PlayerSetupAdapter(private val players: List<Player>) : RecyclerView.Adapter<PlayerSetupHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerSetupHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_player_game_setup, parent, false)
        return PlayerSetupHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlayerSetupHolder, position: Int) {
        val player = players[position]
        holder.setPlayer(player)
    }

    override fun getItemCount(): Int {
        return players.size
    }
}