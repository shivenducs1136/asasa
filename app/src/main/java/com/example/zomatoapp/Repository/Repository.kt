package com.example.zomatoapp.Repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.zomatoapp.FoodService
import com.example.zomatoapp.RestrauntApi.restraunt
import com.example.zomatoapp.api.FoodApi
import com.example.zomatoapp.api.food
import com.example.zomatoapp.cityapi.city
import retrofit2.Response

class Repository(var context: Context) {
    val showProgress = MutableLiveData<Boolean>()
    val cityshowprogress = MutableLiveData<Boolean>()
    var food = MutableLiveData<FoodApi>()

     suspend fun getPost(lat:Double,lon:Double): Response<food> {
        showProgress.value=true
        return FoodService.getClient(context).getFood(lat,lon)
    }
    suspend fun getCity(city: String): Response<city>{
        cityshowprogress.value = true
        return FoodService.getClient(context).getcity(city)
    }
    suspend fun getCityid(cityid:String): Response<food>{
        cityshowprogress.value =true
        return FoodService.getClient(context).getCityid(cityid)
    }
    suspend fun getRestraunt(lat:Double,lon:Double):Response<restraunt>{
//        showProgress.value=true
        return FoodService.getClient(context).getRestraunt(lat,lon)
    }
}