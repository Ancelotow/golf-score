package com.esgi.golf.domain.models

enum class ScoreType(val label: String) {

    Birdie("Birdie"),
    Eagle("Eagle"),
    Albatros("Albatros"),
    Bogey("Bogey"),
    DoubleBogey("Double Bogey"),
    TripleBogey("Triple Bogey"),
    QuadrupleBogey("Quadruple Bogey"),
    QuintupleBogey("Quintuple Bogey"),
    BadHole("Bad Hole"),
    Par("Par"),
    Unknown("Inconnu");

}