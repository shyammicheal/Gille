package com.mindorks.retrofit.coroutines.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getChallengeService() = apiService.getChallengeService()
    suspend fun getEmployees() = apiService.getEmployees()
}