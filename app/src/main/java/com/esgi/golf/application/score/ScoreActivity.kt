package com.esgi.golf.application.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.application.components.player_item.PlayerItemAdapter
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import com.esgi.golf.domain.models.Round

class ScoreActivity : AppCompatActivity() {
    private val items = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    private val holes = listOf(
        Hole(1, 1, "Trou 1", 2),
        Hole(2, 2, "Trou 2", 2),
    )
    private val rounds = listOf(
        Round(Player(1, "John", "Doe"), holes[1], 4, 1),
        Round(Player(1, "John", "Doe"), holes[2], 3, 1),
        Round(Player(2, "David", "Lynch"), holes[1], 3, 2),
        Round(Player(2, "David", "Lynch"), holes[2], 9, 2),

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        supportActionBar?.hide()

        // Spinner
        val spinner: Spinner = findViewById(R.id.hole_spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // TextView
        val tvPlayer: TextView = findViewById(R.id.tv_player)
        tvPlayer.text = getString(R.string.player_text, "12")

        // RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rv_players)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlayerItemAdapter(rounds.toMutableList(), ::addShot, ::removeShot, this)
    }

    fun addShot(round: Round): Unit {
        //round.score += 1
    }

    fun removeShot(round: Round) {
        //round.score -= 1
    }
}