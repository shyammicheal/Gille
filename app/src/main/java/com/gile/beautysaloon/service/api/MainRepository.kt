package com.gile.beautysaloon.service.api

import com.mindorks.retrofit.coroutines.data.api.ApiHelper

class MainRepository constructor(private val retrofitService: ApiHelper) {

    suspend fun getChallengeService() = retrofitService.getChallengeService()
    suspend fun getEmployees() = retrofitService.getEmployees()
}