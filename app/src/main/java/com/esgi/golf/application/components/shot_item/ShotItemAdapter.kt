package com.esgi.golf.application.components.shot_item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Round

class ShotItemAdapter(
    private val rounds: MutableList<Round>,
) : RecyclerView.Adapter<ShotItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShotItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_shot, parent, false)
        return ShotItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShotItemViewHolder, position: Int) {
        holder.setItem(rounds[position])
    }

    override fun getItemCount(): Int {
        return rounds.size
    }
}
