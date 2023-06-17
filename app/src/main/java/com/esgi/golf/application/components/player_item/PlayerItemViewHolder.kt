package com.esgi.golf.application.components.player_item

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Round

class PlayerItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val name = v.findViewById<TextView>(R.id.tv_name)
    private val score = v.findViewById<TextView>(R.id.tv_score)
    private val btnAdd = v.findViewById<ImageButton>(R.id.btn_add)
    private val btnRemove = v.findViewById<ImageButton>(R.id.btn_remove)

    fun setItem(item: Round, addShot: (Round) -> Unit, removeShot: (Round) -> Unit) {
        "${item.player.firstname} ${item.player.name.uppercase()} ".also { name.text = it }
        score.text = item.nbShot.toString()
        btnAdd.setOnClickListener { addShot(item) }
        btnRemove.setOnClickListener { removeShot(item) }
    }

}