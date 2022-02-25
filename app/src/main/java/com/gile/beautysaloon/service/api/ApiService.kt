package com.mindorks.retrofit.coroutines.data.api

import com.gile.beautysaloon.model.ChallengeEmployeeResponse
import com.gile.beautysaloon.model.ChallengeServiceResponse
import retrofit2.http.*

interface ApiService {

    @GET("challenge-services")
    suspend fun getChallengeService(): ChallengeServiceResponse

    @GET("challenge-employees")
    suspend fun getEmployees(): ChallengeEmployeeResponse

}