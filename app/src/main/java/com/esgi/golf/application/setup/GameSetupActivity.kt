package com.esgi.golf.application.setup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R

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




    }
}