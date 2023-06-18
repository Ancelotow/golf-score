package com.esgi.golf.application.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.application.components.game_item.GameListAdapter
import com.esgi.golf.application.setup.GameSetupStatus
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import com.esgi.golf.domain.models.Round
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class GameListActivity : AppCompatActivity() {

    private val viewModel: GameListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val recyclerViewGameList = findViewById<RecyclerView>(R.id.game_list_recycler_view)
        recyclerViewGameList.layoutManager = LinearLayoutManager(this)

        var gameList: MutableList<Game>  = mutableListOf()

        viewModel.getGames()

        viewModel.gameListState.observe(this) {
            when(it.status){
                GameListStatus.Loading -> {
                    Toast.makeText(this, "Recuperation des parties...", Toast.LENGTH_SHORT).show()
                }

                GameListStatus.Success -> {
                    if(it.games == null || it.games.isEmpty()){
                        val emptyTextView = findViewById<TextView>(R.id.game_list_empty)
                        emptyTextView.visibility = View.VISIBLE
                        recyclerViewGameList.visibility = View.GONE
                    }
                    gameList = it.games?.toMutableList() ?: mutableListOf()
                    Log.d("GameListActivity", it.games.toString())
                    val adapter = GameListAdapter(gameList)
                    recyclerViewGameList.adapter = adapter
                    Toast.makeText(this, "Parties recuperées", Toast.LENGTH_SHORT).show()
                }

                GameListStatus.Error -> {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }
                else -> {

                }
            }
        }
    }
}