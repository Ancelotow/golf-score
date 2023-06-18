package com.esgi.golf.application.components.shot_item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Round

class ShotItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val name = v.findViewById<TextView>(R.id.tv_player_name)
    private val score = v.findViewById<TextView>(R.id.tv_player_score)

    fun setItem(item: Round) {
        "${item.player.firstname} ${item.player.name.uppercase()} ".also { name.text = it }
        score.text = item.nbShot.toString()
    }

}