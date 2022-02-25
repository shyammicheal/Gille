package com.gile.beautysaloon.model

data class ChallengeEmployeeResponse(
    val `data`: List<ChallengeEmployeeData>? = listOf(),
    val responseCode: Int? = 0
)