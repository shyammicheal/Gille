package com.gile.beautysaloon.model

data class CartModel(
    val challengeServiceData:MutableList<ChallengeServiceData>,
    val selectedEmployees: MutableList<ChallengeEmployeeData>)