package com.esgi.golf.application.game_report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.application.components.shot_item.ShotItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameReportActivity : AppCompatActivity() {
    private val viewModel: GameReportViewModel by viewModels()

    private lateinit var winnerTeamTextView: TextView
    private lateinit var strokesTextView: TextView
    private lateinit var shotsRecyclerView: RecyclerView

    companion object {
        fun navigateTo(activity: AppCompatActivity, gameId: Int) {
            val intent = Intent(activity, GameReportActivity::class.java)
            intent.putExtra("gameId", gameId)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_report)
        supportActionBar?.hide()

        val gameId = intent.getIntExtra("gameId", -1)
        viewModel.getGame(gameId)
        viewModel.gameState.observe(this) {
            when (it.status) {
                GameReportStatus.Loading -> Toast.makeText(
                    this,
                    "Chargement de la partie...",
                    Toast.LENGTH_SHORT
                ).show()

                GameReportStatus.Error -> Toast.makeText(
                    this,
                    "Une erreur est survenue lors de la récupération de la partie.",
                    Toast.LENGTH_LONG
                ).show()

                GameReportStatus.Success -> {
                    winnerTeamTextView = findViewById(R.id.winnerTeamTextView)
                    strokesTextView = findViewById(R.id.strokesTextView)
                    shotsRecyclerView = findViewById(R.id.shotsRecyclerView)

                    // Mise à jour de l'interface
                    winnerTeamTextView.text =
                        "${it.game!!.winner?.firstname ?: "No winner"} ${it.game!!.winner?.name ?: ""}"
                    strokesTextView.text = "Score : ${it.game.winner?.scoreTotal ?: -1}"


                    // RecyclerView
                    shotsRecyclerView.layoutManager = LinearLayoutManager(this)
                    shotsRecyclerView.adapter = ShotItemAdapter(it.game.rounds.toMutableList())
                }
            }
        }
    }
}