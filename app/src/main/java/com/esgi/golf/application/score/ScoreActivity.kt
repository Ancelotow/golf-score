package com.esgi.golf.application.score

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.application.components.player_item.PlayerItemAdapter
import com.esgi.golf.domain.models.Player

class ScoreActivity : AppCompatActivity() {
    private val items = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    private val players = listOf(
        Player(1, "John", "Doe"),
        Player(2, "John", "Doe"),
        Player(3, "John", "Doe"),
        Player(4, "John", "Doe"),
        Player(5, "John", "Doe"),
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
        recyclerView.adapter = PlayerItemAdapter(players.toMutableList(), this)
    }
}