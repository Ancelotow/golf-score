package com.esgi.golf.application.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.esgi.golf.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
    }
}