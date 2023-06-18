package com.esgi.golf.application.game_report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
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

    // private val shots: MutableList<String> = mutableListOf()
    // private lateinit var shotsAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_report)

        Log.d("GameReportActivity", "oui")

        viewModel.gameState.observe(this) {
            when(it.status) {
                GameReportStatus.Loading -> print("TODO Loading")
                GameReportStatus.Error -> print("TODO Error")
                GameReportStatus.Success -> {
                    winnerTeamTextView = findViewById(R.id.winnerTeamTextView)
                    strokesTextView = findViewById(R.id.strokesTextView)
                    shotsRecyclerView = findViewById(R.id.shotsRecyclerView)

                    // Mise à jour de l'interface
                    winnerTeamTextView.text = "${it.game!!.winner?.firstname ?: "No winner"} ${it.game!!.winner?.name ?: ""}"
                    strokesTextView.text = "TODO 12" // TODO: it.game!!.strokes.toString()

                    Log.d("GameReportActivity", it.game.rounds.toString())

                    // RecyclerView
                    shotsRecyclerView.layoutManager = LinearLayoutManager(this)
                    shotsRecyclerView.adapter = ShotItemAdapter(it.game.rounds.toMutableList())
                }
            }

        }

/*
        val gameReportViewModel = ViewModelProvider(this).get(GameReportViewModel::class.java)

        shotsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, shots)

        gameReportViewModel.shots.observe(this, shotsObserver)



        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, shots)
        shotsListView.adapter = adapter

 */
    }

/*
    private val shotsObserver = object : Observer<List<String>> {
        override fun onChanged(updatedShots: List<String>) {
            shots.clear()
            shots.addAll(updatedShots)
            shotsAdapter.notifyDataSetChanged()
        }
    }
 */
}