package com.gile.beautysaloon.model

data class CartSelectedModel(
    val challengeServiceData:ChallengeServiceData,
    val selectedEmployees: MutableList<ChallengeEmployeeData>)