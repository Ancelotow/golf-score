package com.esgi.golf.application.components.shot_item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Round
import android.content.Context
import com.esgi.golf.domain.models.ScoreType


class ShotItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val context: Context = v.context
    private val roundOrder = v.findViewById<TextView>(R.id.tv_round_text)
    private val shotsNumber = v.findViewById<TextView>(R.id.tv_shots_number)
    private val hole = v.findViewById<TextView>(R.id.tv_hole_text)
    private val type = v.findViewById<TextView>(R.id.tv_shots_type)

    fun setItem(item: Round) {
        // "${item.player.firstname} ${item.player.name.uppercase()} ".also { name.text = it }
        // shotsNumber.text = item.nbShot.toString()

        roundOrder.text = context.getString(R.string.round_order, item.order, item.player.firstname, item.player.name)
        shotsNumber.text = context.getString(R.string.number_of_shots, item.nbShot)
        hole.text = context.getString(R.string.hole, item.hole.name, item.hole.order)
        type.text = item.type.label
    }

}