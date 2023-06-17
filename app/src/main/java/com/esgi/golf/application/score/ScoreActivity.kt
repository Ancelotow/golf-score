package com.esgi.golf.application.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.application.components.player_item.PlayerItemAdapter
import com.esgi.golf.application.home.HomeViewModel
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import com.esgi.golf.domain.models.Round
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScoreActivity : AppCompatActivity() {

    private val viewModel: ScoreViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        supportActionBar?.hide()

        viewModel.gameState.observe(this) {
            when (it.status) {
                ScoreStateStatus.Loading -> {
                    // TODO
                }

                ScoreStateStatus.Success -> {
                    // Spinner
                    val spinner: Spinner = findViewById(R.id.hole_spinner)
                    val adapter =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, it.game!!.holes)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter

                    // TextView
                    val tvPlayer: TextView = findViewById(R.id.tv_player)
                    tvPlayer.text = getString(R.string.player_text, it.game.players.size.toString())

                    // RecyclerView
                    val recyclerView: RecyclerView = findViewById(R.id.rv_players)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter =
                        PlayerItemAdapter(it.game.rounds.filter { e -> e.hole.id == 1 }.toMutableList(), ::addShot, ::removeShot, this)
                }

                ScoreStateStatus.Error -> {
                    // TODO
                }

                else -> {

                }
            }
        }


    }

    fun addShot(round: Round): Unit {
        //round.score += 1
    }

    fun removeShot(round: Round) {
        //round.score -= 1
    }
}