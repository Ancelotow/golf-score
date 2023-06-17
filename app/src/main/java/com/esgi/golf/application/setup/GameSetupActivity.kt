package com.esgi.golf.application.setup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player

class GameSetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_setup)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val playerRecyclerView = findViewById<RecyclerView>(R.id.game_setup_player_recycler_view)
        val playerLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        playerRecyclerView.layoutManager = playerLayoutManager

        val holeRecyclerView = findViewById<RecyclerView>(R.id.game_setup_holes_recycler_view)
        val holeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        holeRecyclerView.layoutManager = holeLayoutManager

        val players: MutableList<Player> = mutableListOf()

        var holes: MutableList<Hole> = mutableListOf()


        var playerAdapter = PlayerSetupAdapter(players)
        playerRecyclerView.adapter = playerAdapter

        var holeAdapter = HoleSetupAdapter(holes)
        holeRecyclerView.adapter = holeAdapter

        val addPlayerButton = findViewById<Button>(R.id.game_setup_add_player_button)

        addPlayerButton.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_player, null)
            dialogBuilder.setView(dialogView)

            val firstname = dialogView.findViewById<EditText>(R.id.dialog_add_player_firstname_edit_text)
            val lastname = dialogView.findViewById<EditText>(R.id.dialog_add_player_lastname_edit_text)

            dialogBuilder.setPositiveButton("Ajouter") { dialog, which ->
                val value1 = firstname.text.toString()
                val value2 = lastname.text.toString()

                Log.d("TAG", "Value 1: $value1")
                Log.d("TAG", "Value 2: $value2")
                players.add(Player(id = players.size + 1, firstname = value1, name = value2))
                playerAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            dialogBuilder.setNegativeButton("Annuler") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = dialogBuilder.create()
            dialog.show()
        }

        val addHoleButton = findViewById<Button>(R.id.game_setup_add_holes_button)

        addHoleButton.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_hole, null)
            dialogBuilder.setView(dialogView)

            val name = dialogView.findViewById<EditText>(R.id.dialog_add_hole_name_edit_text)
            val par = dialogView.findViewById<EditText>(R.id.dialog_add_hole_par_edit_text)
            val order = dialogView.findViewById<EditText>(R.id.dialog_add_hole_order_edit_text)

            dialogBuilder.setPositiveButton("Ajouter") { dialog, which ->
                val value1 = name.text.toString()
                val value2 = par.text
                val value3 = order.text

                holes.add(Hole(id = holes.size + 1, name = value1, par = value2.toString().toInt(), order = value3.toString().toInt()))
                holeAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            dialogBuilder.setNegativeButton("Annuler") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = dialogBuilder.create()
            dialog.show()
        }
    }
}

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
class HoleSetupHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var holeId = v.findViewById<TextView>(R.id.item_hole_game_setup_hole_order_text_view)
    private var holePar = v.findViewById<TextView>(R.id.item_hole_game_setup_hole_par_text_view)
    private var holeName = v.findViewById<TextView>(R.id.item_hole_game_setup_hole_name_text_view)
    fun setHole(hole: Hole){
        holeId.text = hole.id.toString()
        holePar.text = "Par : " + hole.par.toString()
        holeName.text = hole.name
    }
}

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