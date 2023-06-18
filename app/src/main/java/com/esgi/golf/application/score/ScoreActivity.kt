package com.esgi.golf.application.score

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.application.components.player_item.PlayerItemAdapter
import com.esgi.golf.application.game_report.GameReportActivity
import com.esgi.golf.application.score.game_state.GameStateStatus
import com.esgi.golf.application.score.score_state.ScoreStateStatus
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Round
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScoreActivity : AppCompatActivity() {

    private val viewModel: ScoreViewModel by viewModels()
    private var holeSelected: Hole? = null
    private lateinit var spinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvPlayer: TextView
    private lateinit var btnFinish: Button
    private var gameId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.rv_players)
        spinner = findViewById(R.id.hole_spinner)
        tvPlayer = findViewById(R.id.tv_player)
        btnFinish = findViewById(R.id.btn_finish)
        recyclerView.layoutManager = LinearLayoutManager(this)
        intent.getIntExtra("gameId", -1).let {
            Log.d("GameId", it.toString())
            gameId = it
        }

        btnFinish.setOnClickListener {
            finishGame()
        }

        initGame()
        loadScore()
    }

    private fun initGame() {
        viewModel.getGame(gameId)
        viewModel.gameState.observe(this) {
            when (it.status) {
                GameStateStatus.Loading -> {
                    Toast.makeText(this, "Chargement de la partie...", Toast.LENGTH_SHORT).show()
                }

                GameStateStatus.Success -> {
                    Log.d("Game", it.game.toString())
                    // Spinner
                    val adapter =
                        ArrayAdapter(this, android.R.layout.simple_spinner_item, it.game!!.holes)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            holeSelected = spinner.selectedItem as Hole
                            viewModel.getScore(spinner.selectedItem as Hole, gameId)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            holeSelected = spinner.selectedItem as Hole
                            viewModel.getScore(spinner.selectedItem as Hole, gameId)
                        }
                    }
                    spinner.adapter = adapter

                    // TextView
                    tvPlayer.text = getString(R.string.player_text, it.game.players.size.toString())
                }

                GameStateStatus.Error -> {
                    Toast.makeText(
                        this,
                        "Une erreur est survenue lors de l'initialisation de la partie",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {

                }
            }
        }
    }

    private fun loadScore() {
        viewModel.scoreState.observe(this) {
            when (it.status) {
                ScoreStateStatus.Success -> {
                    recyclerView.adapter = PlayerItemAdapter(
                        it.rounds.toMutableList(),
                        ::addShot,
                        ::removeShot,
                        this
                    )
                }

                ScoreStateStatus.Error -> {
                    Log.e("ScoreActivity", it.error.toString())
                    Toast.makeText(
                        this,
                        "Une erreur est survenue lors de la récupération des scores",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {

                }
            }
        }
    }

    private fun addShot(round: Round) {
        viewModel.addShot(round, gameId)
        if (holeSelected != null) {
            viewModel.getScore(holeSelected!!, gameId)
        }
    }

    private fun removeShot(round: Round) {
        viewModel.removeShot(round, gameId)
        if (holeSelected != null) {
            viewModel.getScore(holeSelected!!, gameId)
        }
    }

    private fun finishGame() {
        viewModel.finishGame(gameId)
        val intent = Intent(this, GameReportActivity::class.java)
        intent.putExtra("gameId", gameId)
        startActivity(intent)
    }
}