package com.esgi.golf.application.score

import androidx.lifecycle.ViewModel
import com.esgi.golf.domain.services.ScoreCalculatorService

class ScoreViewModel constructor(
    private val calculatorService: ScoreCalculatorService,
) : ViewModel() {



}