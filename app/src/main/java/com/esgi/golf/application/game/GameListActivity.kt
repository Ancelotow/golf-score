package com.esgi.golf.application.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.domain.models.Game
import com.esgi.golf.domain.models.Hole
import com.esgi.golf.domain.models.Player
import com.esgi.golf.domain.models.Round
import java.time.LocalDate

class GameListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val recyclerViewGameList = findViewById<RecyclerView>(R.id.game_list_recycler_view)
        recyclerViewGameList.layoutManager = LinearLayoutManager(this)

        val gameList = generateFalseGames()
        val adapter = GameListAdapter(gameList)
        recyclerViewGameList.adapter = adapter
    }
}



class GameListAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_game_list, parent, false)
        return GameListHolder(view)
    }

    override fun onBindViewHolder(holder: GameListHolder, position: Int) {
        holder.setGame(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}


class GameListHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var gameName = v.findViewById<TextView>(R.id.item_game_list_game_name_text_view)
    private var gameDate = v.findViewById<TextView>(R.id.item_game_list_game_date_text_view)
    private var gameWinner = v.findViewById<TextView>(R.id.item_game_list_game_winner_text_view)
    fun setGame(game: Game){
        gameName.text = game.name
        gameDate.text = game.date.toString()
        gameWinner.text = "Winner : ${game.winner?.name ?: ""} ${game.winner?.firstname ?: ""}"
    }
}

fun generateFalseGames(): List<Game> {
    val games = mutableListOf<Game>()

    val players = listOf(
        Player(1, "John", "Doe", 0),
        Player(2, "Emily", "Doe", 0),
        Player(3, "David", "Doe", 0),
        Player(4, "Sophia", "Doe", 0),
    )

    val holes = listOf(
        Hole(1, 1, "Par 3", 3),
        Hole(2, 2, "Par 4", 4),
        Hole(3, 3, "Par 5", 5),
        Hole(4, 4, "Par 3", 3),
        Hole(5, 5, "Par 4", 4),
        Hole(6, 6, "Par 5", 5),
        Hole(7, 7, "Par 3", 3),
        Hole(8, 8, "Par 4", 4),
        Hole(9, 9, "Par 5", 5),
        Hole(10, 10, "Par 3", 3),
        Hole(11, 11, "Par 4", 4),
        Hole(12, 12, "Par 5", 5),
        Hole(13, 13, "Par 3", 3),
        Hole(14, 14, "Par 4", 4),
        Hole(15, 15, "Par 5", 5),
        Hole(16, 16, "Par 3", 3),
        Hole(17, 17, "Par 4", 4),
        Hole(18, 18, "Par 5", 5)
    )

    val rounds = listOf(
        Round(players[0], holes[0], 4, 1, 0),
        Round(players[0], holes[1], 3, 2, 0),
        Round(players[0], holes[2], 5, 3, 0),
        Round(players[1], holes[0], 3, 4, 0),
        Round(players[1], holes[1], 4, 5, 0),
        Round(players[1], holes[2], 6, 6, 0),
        Round(players[2], holes[0], 5, 7, 0),
        Round(players[2], holes[1], 2, 8, 0),
        Round(players[2], holes[2], 4, 9, 0),
        Round(players[3], holes[0], 2, 10, 0),
        Round(players[3], holes[1], 3, 11, 0),
        Round(players[3], holes[2], 5, 12, 0),
    )

    games.add(
        Game(
            1,
            "Game 1",
            players,
            holes,
            rounds,
            Player(1, "John", "Doe", 0),
            LocalDate.now()
        )
    )

    games.add(
        Game(
            2,
            "Game 2",
            players,
            holes,
            rounds,
            Player(2, "Emily", "Doe", 0),
            LocalDate.now()
        )
    )

    games.add(
        Game(
            3,
            "Game 3",
            players,
            holes,
            rounds,
            Player(3, "David", "Doe", 0),
            LocalDate.now()
        )
    )

    return games
}

