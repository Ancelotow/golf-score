package com.esgi.golf.application.components.hole_setup_item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Hole

class HoleSetupAdapter(private val holes: List<Hole>) : RecyclerView.Adapter<HoleSetupHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoleSetupHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_hole_game_setup, parent, false)
        return HoleSetupHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: HoleSetupHolder, position: Int) {
        val hole = holes[position]
        holder.setHole(hole)
    }

    override fun getItemCount(): Int {
        return holes.size
    }
}