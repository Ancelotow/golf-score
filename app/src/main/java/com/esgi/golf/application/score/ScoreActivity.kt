package com.esgi.golf.application.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
    var holeSelected: Hole? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        supportActionBar?.hide()

        val spinner: Spinner = findViewById(R.id.hole_spinner)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                holeSelected = spinner.selectedItem as Hole
                viewModel.getGame()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                holeSelected = null
            }
        }
        val tvPlayer: TextView = findViewById(R.id.tv_player)
        val recyclerView: RecyclerView = findViewById(R.id.rv_players)
        viewModel.gameState.observe(this) {
            when (it.status) {
                ScoreStateStatus.Loading -> {
                    // TODO
                }

                ScoreStateStatus.Success -> {
                    // Spinner
                    val adapter =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, it.game!!.holes)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter

                    // TextView
                    tvPlayer.text = getString(R.string.player_text, it.game.players.size.toString())

                    // RecyclerView
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter =
                        PlayerItemAdapter(
                            it.game.rounds.filter {
                                    e -> e.hole.id == (holeSelected?.id ?: 1)
                            }.toMutableList(),
                            ::addShot,
                            ::removeShot,
                            this
                        )
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
        viewModel.addShot(round)
        viewModel.getGame()
    }

    fun removeShot(round: Round) {
        viewModel.removeShot(round)
        viewModel.getGame()
    }
}