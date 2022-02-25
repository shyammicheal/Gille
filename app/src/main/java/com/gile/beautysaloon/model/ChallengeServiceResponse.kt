package com.gile.beautysaloon.model

import java.io.Serializable

data class ChallengeServiceResponse(
    val data: List<ChallengeServiceData>? = listOf(),
    val responseCode: Int? = 0
) : Serializable