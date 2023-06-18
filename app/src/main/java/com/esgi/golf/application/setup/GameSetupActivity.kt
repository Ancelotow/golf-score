package com.esgi.golf.application.setup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.application.components.hole_setup_item.HoleSetupAdapter
import com.esgi.golf.application.components.player_setup_item.PlayerSetupAdapter
import com.esgi.golf.application.score.ScoreActivity
import com.esgi.golf.domain.builder.GameBuilder
import com.esgi.golf.domain.builder.GameBuilderDefault
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

@AndroidEntryPoint
class GameSetupActivity : AppCompatActivity() {

    private val builder: GameBuilder = GameBuilderDefault()
    private val viewModel: GameSetupViewModel by viewModels()

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
                players.add(Player(id = players.size + 1, firstname = value1, name = value2, 0))
                playerAdapter.notifyDataSetChanged()
                builder.addPlayer(Player(id = players.size + 1, firstname = value1, name = value2, scoreTotal = 0))
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
                builder.addHole(Hole(id = holes.size + 1, name = value1, par = value2.toString().toInt(), order = value3.toString().toInt()))
                dialog.dismiss()
            }

            dialogBuilder.setNegativeButton("Annuler") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = dialogBuilder.create()
            dialog.show()
        }

        val startGameButton = findViewById<Button>(R.id.game_setup_start_game_button)

        startGameButton.setOnClickListener{
            val gameNameEditText = findViewById<EditText>(R.id.game_setup_game_name_edit_text)
            val gameName = gameNameEditText.text.toString()
            if(gameName != ""){
                builder.buildName(gameName)
                builder.buildDate(LocalDate.now())
                val game = builder.build()
                viewModel.addGame(game)
            }
        }
        val loadingView = findViewById<View>(R.id.game_setup_start_loading_progress_bar)

        viewModel.gameSetupState.observe(this) {
            when(it.status){
                GameSetupStatus.Loading -> {
                    Log.d("TAG", "Loading")
                    loadingView.visibility = View.VISIBLE
                    startGameButton.visibility = View.GONE
                    Toast.makeText(this, "Création de la partie", Toast.LENGTH_LONG).show()
                }
                GameSetupStatus.Success -> {
                    Log.d("TAG", "Game created ${it.gameID}")
                    loadingView.visibility = View.GONE
                    startGameButton.visibility = View.VISIBLE
                    Toast.makeText(this, "Partie créée ${it.gameID}", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, ScoreActivity::class.java)
                    intent.putExtra("gameId", it.gameID)
                    startActivity(intent)
                }
                GameSetupStatus.Error -> {
                    Log.d("TAG", "Error creating game")
                    loadingView.visibility = View.GONE
                    startGameButton.visibility = View.VISIBLE
                    Toast.makeText(this, "Erreur lors de la création de la partie", Toast.LENGTH_LONG).show()
                }
                else -> {

                }
            }
        }

    }
}


