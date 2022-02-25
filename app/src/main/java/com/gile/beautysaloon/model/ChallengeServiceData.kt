package com.gile.beautysaloon.model

import java.io.Serializable

data class ChallengeServiceData(
    val id: Int? = 0,
    val image: String? = "",
    val name: String? = "",
    val price: Int? = 0
) : Serializable