package com.esgi.golf.application.components.player_item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Player

class PlayerItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val name = v.findViewById<TextView>(R.id.tv_name)
    private val score = v.findViewById<TextView>(R.id.tv_score)

    fun setItem(item: Player) {
        "${item.firstname} ${item.name.uppercase()} ".also { name.text = it }
        score.text = "12"
    }

}