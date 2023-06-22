package com.esgi.golf.application.home

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.esgi.golf.R
import com.esgi.golf.application.game.GameListActivity
import com.esgi.golf.application.setup.GameSetupActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var buttonSave: Button
    private lateinit var buttonLoad: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        buttonSave = findViewById(R.id.button_new)
        buttonLoad = findViewById(R.id.button_load)
        buttonSave.setOnClickListener(::newGame)
        buttonLoad.setOnClickListener(::loadGame)
    }

    private fun newGame(view: View) {
        GameSetupActivity.navigateTo(this)
    }

    private fun loadGame(view: View) {
        GameListActivity.navigateTo(this)
    }
}