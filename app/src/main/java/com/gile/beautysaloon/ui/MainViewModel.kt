package com.gile.beautysaloon.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gile.beautysaloon.service.api.MainRepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.liveData
import com.gile.beautysaloon.model.CartModel
import com.gile.beautysaloon.model.CartSelectedModel
import com.gile.beautysaloon.utils.Resource
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {


    fun getChallengeService() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getChallengeService()))
        } catch (exception: Throwable) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getEmployees() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getEmployees()))
        } catch (exception: Throwable) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody?): String? {
        return try {
            val jsonObject = JSONObject(responseBody?.string())
            jsonObject.getString("error_msg")
        } catch (e: Exception) {
            e.message
        }
    }

}
