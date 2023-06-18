package com.esgi.golf.application.components.player_setup_item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Player

class PlayerSetupHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var playerId = v.findViewById<TextView>(R.id.item_player_game_setup_player_id_text_view)
    private var playerFirstname = v.findViewById<TextView>(R.id.item_player_game_setup_player_firstname_text_view)
    private var playerLastname = v.findViewById<TextView>(R.id.item_player_game_setup_player_lastname_text_view)
    fun setPlayer(player: Player){
        playerId.text = player.id.toString()
        playerFirstname.text = player.firstname
        playerLastname.text = player.name
    }
}