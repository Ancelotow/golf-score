package com.esgi.golf.application.components.player_item

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Player
import com.esgi.golf.domain.models.Round

class PlayerItemAdapter(
    private val players: MutableList<Round>,
    private val addShot: (Round) -> Unit,
    private val removeShot: (Round) -> Unit,
    private val activity: Activity?
) : RecyclerView.Adapter<PlayerItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_player, parent, false)
        return PlayerItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerItemViewHolder, position: Int) {
        holder.setItem(players[position], addShot, removeShot)
    }

    override fun getItemCount(): Int {
        return players.size
    }

}