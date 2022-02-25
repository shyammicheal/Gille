package com.gile.beautysaloon.model

data class ChallengeEmployeeData(
    val id: Int = 0,
    val image: String? = "",
    val name: String? = "",
    var selected: Boolean = false
)