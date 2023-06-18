package com.esgi.golf.application.components.hole_setup_item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Hole

class HoleSetupHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var holeId = v.findViewById<TextView>(R.id.item_hole_game_setup_hole_order_text_view)
    private var holePar = v.findViewById<TextView>(R.id.item_hole_game_setup_hole_par_text_view)
    private var holeName = v.findViewById<TextView>(R.id.item_hole_game_setup_hole_name_text_view)
    fun setHole(hole: Hole){
        holeId.text = hole.order.toString()
        holePar.text = "Par : " + hole.par.toString()
        holeName.text = hole.name
    }
}
